package com.example.rentread.RentRead.Exception;

public class BookNotAvailableToRentException extends RuntimeException{

    public BookNotAvailableToRentException(int id){
        super("Book with id " + id + " is not available to rent");
    }

}
