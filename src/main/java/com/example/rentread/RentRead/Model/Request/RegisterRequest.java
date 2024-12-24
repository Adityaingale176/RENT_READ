package com.example.rentread.RentRead.Model.Request;

import com.example.rentread.RentRead.Model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    
    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;
}
