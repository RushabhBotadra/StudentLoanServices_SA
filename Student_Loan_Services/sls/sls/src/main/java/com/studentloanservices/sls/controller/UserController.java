package com.studentloanservices.sls.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studentloanservices.sls.model.User;
import com.studentloanservices.sls.service.UserService;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User rep) {
//        service.registerRepresentative(rep);
//        return ResponseEntity.ok("Representative registered successfully");
    	Map<String, Object> response = new HashMap<>();
        try {
            if (rep == null || rep.getEmail() == null || rep.getEmail().trim().isEmpty() ||
                rep.getPassword() == null || rep.getPassword().trim().isEmpty()) {
                response.put("message", "Email and password are required.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            service.registerRepresentative(rep);
            response.put("success", true);
            response.put("message", "Representative Registered Successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
        	response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
        	response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login( @RequestParam String email, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                response.put("message", "Email and password are required.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            User user = service.login(email, password);
            if (user != null) {
            	response.put("success", true);
                response.put("message", "Login Successful");
                response.put("user", user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
            	response.put("success", false);
                response.put("message", "Invalid email or password.");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException e) {     
        	response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {   
        	response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}