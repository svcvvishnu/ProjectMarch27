package com.csci5308.w22.wiseshopping.service;

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
import java.util.Optional;

/**
 * @author Pavithra Gunasekaran
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * checks the login credentials of a user
     *
     * @param email    email id of the user
     * @param password password of the user
     */
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


