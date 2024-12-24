package com.example.rentread.RentRead.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.rentread.RentRead.Model.User;

public interface iUserRepository extends JpaRepository<User, Integer>{

    UserDetails findByEmail(String username);

}
