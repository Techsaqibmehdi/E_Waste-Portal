package com.ewasteportal.service;

import com.ewasteportal.model.User;
import com.ewasteportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setAddress(userDetails.getAddress());
        user.setPhone(userDetails.getPhone());
        user.setCity(userDetails.getCity());
        
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        userRepository.delete(user);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
    
    public User registerUser(User user) {
        if (emailExists(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        return userRepository.save(user);
    }
    
    public User loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return user.get();
        }
        throw new RuntimeException("Invalid email or password");
    }
    
    public long getTotalUsers() {
        return userRepository.count();
    }
}