RentRead

A book rental service application built with Spring Boot. The system allows users to rent and return books. It features functionalities like user registration, book availability, and rental status tracking.

Features
	•	Book Management: Add, update, rent, and return books.
	•	User Management: Register, login, and manage user details.
	•	Logging: Activity and error logs using SLF4J.
	•	Database Integration: Persistent storage using Spring Data JPA with relational database support (MySQL).

Technologies Used
	•	Spring Boot: Java framework to create stand-alone, production-grade applications.
	•	Spring Data JPA: Simplifies data persistence and repository management.
	•	SLF4J with Logback: Used for logging and debugging.
	•	JUnit and Mockito: Unit testing and mocking dependencies.

Prerequisites

    Before running the project, ensure you have the following tools installed:
        •	Java 17 or above
        •	Maven or Gradle (depending on your build tool)
        •	IDE (e.g., IntelliJ IDEA, Eclipse)
        •	MySQL or H2 Database (For production or testing environment)

Getting Started

    1.  Clone the repository
        https://github.com/Adityaingale176/RENT_READ.git
    
    2. CREATE DATABASE rentread;

    3. Update the database connection properties in src/main/resources/application.properties:
        spring.datasource.url=jdbc:mysql://localhost:3306/rentread
        spring.datasource.username=root
        spring.datasource.password=root

    4. Build Project
        ./gradlew build

    5. Run application
        ./gradlew bootRun   
    
    5. Test Endpoints using postman collection -> RentRead.postman_collection.json


Endpoints
	•	POST /register: Register a new user.
	•	POST /login: User login.
	•	GET /books: Retrieve a list of available books.
	•	POST /books/rent: Rent a book.
	•	POST /books/return: Return a rented book.

Authorization Implementation

    - Basic auth is implemented with role based authorization. It is Having "USER" & "ADMIN" roles.
    - Only registered users having "ADMIN" Authority can access 
        POST /storeBookDetails() -> creating new Books
        POST /register -> Register a new user.
    - Anyone can access "/login" and "/register" API
    - Rest of the API can be accessed by normal user having "USER" Authority

