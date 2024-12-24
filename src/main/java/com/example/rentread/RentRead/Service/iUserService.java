package com.example.rentread.RentRead.Service;

import java.util.List;

import com.example.rentread.RentRead.Model.User;

public interface iUserService {

    public User registerUser(User user);

    public User updateUser(int id, User user);

    public void deleteUser(int id);

    public User getUser(int id);

    public List<User> getAllUsers();

}
