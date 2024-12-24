package com.example.rentread.RentRead.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rentread.RentRead.Model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {


}
