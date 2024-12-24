package com.example.rentread.RentRead.Exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(int id){
        super("User with id " + id + " not found");
    }
}
