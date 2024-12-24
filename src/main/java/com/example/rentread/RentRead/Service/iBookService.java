package com.example.rentread.RentRead.Service;

import java.util.List;

import com.example.rentread.RentRead.Model.Book;

public interface iBookService {
    
    public Book storeBookDetails(Book book);

    public Book updateBookDetails(int id, Book book);

    public void deleteBookDetails(int id);

    public Book getBookDetails(int id);

    public List<Book> getAllBooksDetails();
}
