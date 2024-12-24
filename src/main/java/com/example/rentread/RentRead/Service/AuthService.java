package com.example.rentread.RentRead.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.example.rentread.RentRead.Model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rentread.RentRead.Model.Role;
import com.example.rentread.RentRead.Model.Request.AuthRequest;
import com.example.rentread.RentRead.Model.Request.RegisterRequest;
import com.example.rentread.RentRead.Model.Response.AuthResponse;
import com.example.rentread.RentRead.Repository.iUserRepository;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    iUserRepository userRepository;

    @Autowired
    AuthenticationManager authenticatoinManager;

    public AuthResponse register(RegisterRequest request){
        if(request.getRole() == null){
            request.setRole(Role.USER);
        }

        User user = User.builder().
                                email(request.getEmail()).
                                password(passwordEncoder.encode(request.getPassword())).
                                firstName(request.getFirstName()).
                                lastName(request.getLastName()).
                                role(request.getRole())
                                .build();

        userRepository.save(user);

        return AuthResponse.builder().build();

    }

    public AuthResponse login(AuthRequest request){
        authenticatoinManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return AuthResponse.builder().build();
    }

}
