package com.csci5308.w22.wiseshopping.service;


import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.UserRepository;

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

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;


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

    public void setUp() {
        user = new User("johndoe@xyz.com", "Password123!");
    }



    @Test
    public void testUpdateUserDetails() {
        when(mockedUserRepository.findByEmail(any(String.class))).thenReturn(user);

        //Getting the updated user
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put(UserService.FIRST_NAME, "John");
        userDetails.put(UserService.LAST_NAME, "Doe");
        userDetails.put(UserService.CONTACT, "9096754432");
        User updatedUser = userService.updateUserDetails("johndoe@xyz.com", userDetails);

        //check if response is not null
        Assertions.assertNotNull(updatedUser);

        //Check if firstname,lastname and contact are updated
        Assertions.assertEquals("John", updatedUser.getUserFirstName());
        Assertions.assertEquals("Doe", updatedUser.getUserLastName());
        Assertions.assertEquals("johndoe@xyz.com", updatedUser.getEmail());
        Assertions.assertEquals("9096754432", updatedUser.getContact());

    }


    @Test
    public void testUpdateUserDetailsInvalidFirstName() {
        when(mockedUserRepository.findByEmail(any(String.class))).thenReturn(user);
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put(UserService.FIRST_NAME, "");
        IllegalArgumentException ex = Assertions.assertThrows( IllegalArgumentException.class,
                () -> userService.updateUserDetails("johndoe@xyz.com", userDetails), "Exception not thrown");
        Assertions.assertTrue(ex.getMessage().contains(UserService.FIRST_NAME + " cannot be null or empty or blank"));
    }

    @Test
    public void testUpdateUserDetailsInvalidLastName() {
        when(mockedUserRepository.findByEmail(any(String.class))).thenReturn(user);
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put(UserService.LAST_NAME, "");
        IllegalArgumentException ex = Assertions.assertThrows( IllegalArgumentException.class,
                () -> userService.updateUserDetails("johndoe@xyz.com", userDetails), "Exception not thrown");
        Assertions.assertTrue(ex.getMessage().contains(UserService.LAST_NAME + " cannot be null or empty or blank"));
    }

    @Test
    public void testUpdateUserDetailsInvalidContact() {
        when(mockedUserRepository.findByEmail(any(String.class))).thenReturn(user);
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put(UserService.CONTACT, "");
        IllegalArgumentException ex = Assertions.assertThrows( IllegalArgumentException.class,
                () -> userService.updateUserDetails("johndoe@xyz.com", userDetails), "Exception not thrown");
        Assertions.assertTrue(ex.getMessage().contains(UserService.CONTACT + " cannot be null or empty or blank"));
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

