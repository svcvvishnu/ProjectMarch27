package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Pavithra Gunasekaran
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class UserServiceTests {

        @Autowired
        private UserService userService;

        private User user;

        @BeforeEach
        public void setUp(){
            user = new User("johndoe@xyz.com",  "Password123!");
        }

        @Test
        public void testLoginUser(){
            User actualUser = userService.loginUser("johndoe@xyz.com","Password123!");
            Assertions.assertEquals(user.getEmail(),actualUser.getEmail());

        }



    }

