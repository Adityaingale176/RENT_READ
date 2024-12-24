package com.example.rentread.RentRead.Model;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private int bookId;

    private String title;

    private String author;

    private String genre;

    private boolean availabilityStatus;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;

}
