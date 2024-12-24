package com.example.rentread.RentRead.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.rentread.RentRead.Exception.UserNotFoundException;
import com.example.rentread.RentRead.Model.Role;
import com.example.rentread.RentRead.Model.User;
import com.example.rentread.RentRead.Repository.iUserRepository;

@Service
public class UserService implements iUserService, UserDetailsService{

    private iUserRepository userRepository;

    public UserService(iUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) {
        if (user.getRole()==null){
            user.setRole(Role.USER);
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(int id, User user) {
        User fetchedUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        fetchedUser.setFirstName(user.getFirstName());
        fetchedUser.setEmail(user.getEmail());
        fetchedUser.setPassword(user.getPassword());
        return userRepository.save(fetchedUser);
    }

    @Override
    public void deleteUser(int id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id); 
        }
        else{
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
