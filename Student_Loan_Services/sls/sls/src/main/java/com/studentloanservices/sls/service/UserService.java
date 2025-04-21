package com.studentloanservices.sls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.studentloanservices.sls.dao.UserDAO;
import com.studentloanservices.sls.model.User;


@Service
public class UserService {
    
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerRepresentative(User user) {
        
        try {            
        	String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            
            int rowsAffected = userDAO.save(user);
            if (rowsAffected != 1) {
                throw new RuntimeException("Failed to save user");
            }

            // Get the last inserted user ID
//            int userId = userDAO.getLastInsertedUserId();
            User currentUser = userDAO.findByEmail(user.getEmail());
            if (currentUser == null) {
                throw new RuntimeException("Failed to retrieve user ID after registration");
            }

            // Insert bank representative
            rowsAffected = userDAO.insertBankRepresentative(currentUser.getUserId());
            if (rowsAffected != 1) {
                throw new RuntimeException("Failed to insert bank representative");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Registration failed: " + e.getMessage(), e);
        }
    }


    public User login(String email, String password) {
        try {
            User user = userDAO.findByEmail(email);
            if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
                return null;
            }
            return user;
        } catch (DataAccessException e) {
            throw new RuntimeException("User Not Registered");
        }
    }
}