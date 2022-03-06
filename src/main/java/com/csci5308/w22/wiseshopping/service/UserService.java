package com.csci5308.w22.wiseshopping.service;


import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.MerchantRepository;
import com.csci5308.w22.wiseshopping.repository.UserRepository;

import com.trilead.ssh2.auth.AuthenticationManager;

import com.csci5308.w22.wiseshopping.utils.Util;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;


import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.MerchantRepository;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import com.trilead.ssh2.auth.AuthenticationManager;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;

import java.util.Map;
import java.util.Optional;

//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;



import java.util.Optional;

/**
 * @author Pavithra Gunasekaran
 */
@Service
public class UserService {


    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String CONTACT = "contact";

    @Autowired
    UserRepository userRepository;

    /**
     * checks the login credentials of a user
     *
     * @param email    email id of the user
     */



    @Transactional
    public User updateUserDetails(String email, Map<String, String> userDetails) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new IllegalArgumentException("Could not find any user with email id:" + email);
        }

        for (Map.Entry<String, String> entry : userDetails.entrySet()) {
            if (entry.getValue() == null || entry.getValue().isEmpty() || entry.getValue().isBlank()) {
                throw new IllegalArgumentException(entry.getKey() + " cannot be null or empty or blank");
            }
            switch (entry.getKey()) {
                case FIRST_NAME:
                    user.setUserFirstName(entry.getValue());
                    break;
                case LAST_NAME:
                    user.setUserLastName(entry.getValue());
                    break;
                case CONTACT:
                    user.setContact(entry.getValue());
                    break;
                default:
                    //log the invalid input value
            }
        }

        userRepository.save(user);
        return user;//
    }

    @Transactional
    public User loginUser(String email, String password) {
        if (email == null) {
            throw new NullPointerException("email cannot be null");
        }
        if (email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be empty");
        }

        if (email == null || email.isBlank() || email.isEmpty()) {
            throw new IllegalArgumentException("email cannot be null or empty or blank");

        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("given email id is not valid");
        }

        if (password == null) {
            throw new NullPointerException("password cannot be null");
        }
        if (password.isBlank() || password.isEmpty()) {
            throw new IllegalArgumentException("password cannot be empty");
        }
        User user = userRepository.findByEmailAndPassword(email, password);
        return user;

    }

    public User registerUser(String name, String email, String password) {
        //TODO implementation
        User user =  new User(1);

        return user;
    }
}


