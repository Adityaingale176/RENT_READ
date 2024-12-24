package com.example.rentread.RentRead.Exception;

public class BookRentLimitExceededException extends RuntimeException{

    public BookRentLimitExceededException(){
        super("Book rent limit exceeded :  cannot have more than two active rentals");
    }

}
