package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.StoreRepository;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import com.csci5308.w22.wiseshopping.utils.Util;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.internal.matchers.Null;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Pavithra Gunasekaran
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    UserRepository mockedUserRepository;

    @InjectMocks
    private UserService userService;

    private User user;


    @BeforeEach
    public void setUp(){
        user = new User("John Doe","Password123!");
    }

    @Test
    public void testLoginUser()  {
        when(mockedUserRepository.findByEmailAndPassword(any(String.class),any(String.class))).thenReturn(user);

        User actualUser = userService.loginUser("johndoe@xyz.com", "password123");
        Assertions.assertEquals(user, actualUser);
    }

    @Test
    public void testInputParametersForLoginUser(){


        NullPointerException emailNullException=Assertions.assertThrows(NullPointerException.class, () -> userService.loginUser(null,"test_password"));
        Assertions.assertEquals("email cannot be null",emailNullException.getMessage());
        IllegalArgumentException emailEmptyException=Assertions.assertThrows(IllegalArgumentException.class, () -> userService.loginUser("","test_password"));
        Assertions.assertEquals("email cannot be empty",emailEmptyException.getMessage());
        IllegalArgumentException emailMissingDomainNameException=Assertions.assertThrows(IllegalArgumentException.class, () -> userService.loginUser("test_email","test_password"));
        Assertions.assertEquals("given email id is not valid",emailMissingDomainNameException.getMessage());

        IllegalArgumentException passwordEmptyException=Assertions.assertThrows(IllegalArgumentException.class, () -> userService.loginUser("test_email@xyz.com",""));
        Assertions.assertEquals("password cannot be empty",passwordEmptyException.getMessage());
        NullPointerException passwordNullException=Assertions.assertThrows(NullPointerException.class, () -> userService.loginUser("test_email@xyz.com",null));
        Assertions.assertEquals("password cannot be null",passwordNullException.getMessage());



    }


    }