package com.FP_Final.FP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.FP_Final.FP.model.Users;
import com.FP_Final.FP.repository.UserRepository;


import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository1;
	
	
	@Autowired
    private UserRepository userRepository;

    // Método para obtener todos los usuarios
    public List<Users> getAllUsers() {
        return userRepository1.findAll();
    }

    // Método para obtener un usuario por su username
    public Optional<Users> getUserByUsername(String username) {
        return userRepository1.findByUsername(username);
    }
	 
	   

}
