package com.example.rentread.RentRead.Exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(int id){
        super("No book found for given id : " + id );
    }
}
