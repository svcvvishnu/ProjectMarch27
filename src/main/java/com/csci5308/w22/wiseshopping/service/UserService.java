package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.MerchantRepository;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pavithra Gunasekaran
 */
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * checks the login credentials of a user
     *
     * @param email    email id of the user
     * @param password password of the user
     * @param userName name of the user
     */

    // Method written by Harsh

    @Transactional
    public User registerUser(String userName, String email, String password) {
        if (userName == null || userName.isEmpty() || userName.isBlank()) {
            throw new IllegalArgumentException("Username cannot be null or empty or blank");
        }
        if (password == null || password.isEmpty() || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty or blank");
        }

        if (email == null || email.isBlank() || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty or blank");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Given email is not valid");
        }

        User user1 = new User(userName, email, password);

        userRepository.save(user1);
        return user1;
    }



}


