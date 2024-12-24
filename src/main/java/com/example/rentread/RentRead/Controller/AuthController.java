package com.example.rentread.RentRead.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.rentread.RentRead.Model.Request.AuthRequest;
import com.example.rentread.RentRead.Model.Request.RegisterRequest;
import com.example.rentread.RentRead.Model.Response.AuthResponse;
import com.example.rentread.RentRead.Service.AuthService;

@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

}
