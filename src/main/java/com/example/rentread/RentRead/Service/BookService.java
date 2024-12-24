package com.example.rentread.RentRead.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rentread.RentRead.Exception.BookNotAvailableToRentException;
import com.example.rentread.RentRead.Exception.BookNotFoundException;
import com.example.rentread.RentRead.Exception.BookRentLimitExceededException;
import com.example.rentread.RentRead.Exception.UserNotFoundException;
import com.example.rentread.RentRead.Model.Book;
import com.example.rentread.RentRead.Model.User;
import com.example.rentread.RentRead.Repository.BookRepository;
import com.example.rentread.RentRead.Repository.iUserRepository;

@Service
public class BookService implements iBookService{

    private static Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private iUserRepository userRepository;

    public BookService(BookRepository bookRepository, iUserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Book storeBookDetails(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBookDetails(int id, Book book) {
        Book fetchedBook = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        fetchedBook.setTitle(book.getTitle());
        fetchedBook.setAuthor(book.getAuthor());
        fetchedBook.setGenre(book.getGenre());
        fetchedBook.setAvailabilityStatus(book.isAvailabilityStatus());
        return fetchedBook;
    }

    @Override
    public void deleteBookDetails(int id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        }
        else throw new BookNotFoundException(id);
    }

    @Override
    public Book getBookDetails(int id) {
        return bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
    }

    @Override
    public List<Book> getAllBooksDetails() {
        return bookRepository.findAll();    
    }

    public Book rentBook(int bookId, int userId) {

       logger.info("Renting a book");
    
       Book book = bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));
       boolean status = book.isAvailabilityStatus();
       User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));

       List<Book> bookList = bookRepository.findAll();
       
       long count = bookList.stream().filter(b -> b.getUser() != null && b.getUser().getUserId()==userId).count();
        logger.info("Count of books currently rented by user = " + count);
        if(status==true && count<2){
            book.setUser(user);
            book.setAvailabilityStatus(false);
            bookRepository.save(book);
        }
        else if(status == false){
            logger.error("Book is not available to rent");;
            throw new BookNotAvailableToRentException(bookId);
        }
        else if(count>=2){
            logger.error("Book rent limit is exceeded");
            throw new BookRentLimitExceededException();
        }
       return book;
    }

    public Book returnBook(int bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));
        if(book.getUser()==null){
            logger.error("Book is not rented");
            throw new RuntimeException("Book is not yet rented");
        }
        book.setUser(null);
        book.setAvailabilityStatus(true);
        bookRepository.save(book);
        return book;
    }
}
