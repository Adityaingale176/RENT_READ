package com.example.rentread.RentRead;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.rentread.RentRead.Model.Book;
import com.example.rentread.RentRead.Model.User;
import com.example.rentread.RentRead.Repository.BookRepository;
import com.example.rentread.RentRead.Repository.iUserRepository;
import com.example.rentread.RentRead.Service.AuthService;
import com.example.rentread.RentRead.Service.BookService;

@ExtendWith(MockitoExtension.class)
public class TestBookService {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private iUserRepository userRepository;

    @InjectMocks
    private BookService bookService;

    @InjectMocks
    private AuthService authService;

    @Mock
    Logger logger;

    @Autowired
    private MockMvc mockMvc;

    private Book book;
    private User user;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setBookId(1);
        book.setAvailabilityStatus(true);

        user = new User();
        user.setUserId(1);
        book.setUser(user);
    }

    @Test
    public void testRentBook() {
        // Mock repository behavior
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Call the method
        Book rentedBook = bookService.rentBook(1, 1);

        // Verify the results
        assertNotNull(rentedBook);
        assertEquals(user, rentedBook.getUser());
        assertFalse(rentedBook.isAvailabilityStatus());

        // Verify repository interactions
        verify(bookRepository, times(1)).save(rentedBook);
    }

    @Test
    public void returnBookTest(){

        Mockito.when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Book rentedBook = bookService.rentBook(1, 1);
        Book returnedBook = bookService.returnBook(1);

        assertNotNull(returnedBook);
        assertEquals(true, returnedBook.isAvailabilityStatus());
    }
}