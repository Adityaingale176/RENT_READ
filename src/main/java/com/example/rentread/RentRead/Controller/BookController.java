package com.example.rentread.RentRead.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rentread.RentRead.Model.Book;
import com.example.rentread.RentRead.Service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/admin")
    public ResponseEntity storeBookDetails(@RequestBody Book book){
        Book savedBook = bookService.storeBookDetails(book);
        return ResponseEntity.status(HttpStatus.OK).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBookDetails(@PathVariable int id , @RequestBody Book book){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBookDetails(id ,book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBookDetails(@PathVariable int id ){
        bookService.deleteBookDetails(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookDetails(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookDetails(id));
    }

    @GetMapping()
    public ResponseEntity getAllBooksDetails(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooksDetails());
    }

    // For renting a book
    @PutMapping("/{bookId}/rent")
    public ResponseEntity<Book> rentBook(@PathVariable int bookId, @RequestParam int userId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.rentBook(bookId, userId));
    }   

    //For returning book
    @PutMapping("/{bookId}/return")
    public ResponseEntity returnBook(@PathVariable int bookId){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.returnBook(bookId));
    }
}
