package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.UserService;
import com.csci5308.w22.wiseshopping.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

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
        user = new User("John","Doe", "zig@zag1.com", "zigzag","1234567890");
    }

    @Test
    public void testLoginUser(){
        User actualUser = userService.loginUser("zig@zag1.com","zigzag");
        Assertions.assertEquals(user.getEmail(),actualUser.getEmail());

    }

//Integration tests for update user
    @Test
    public void testUpdateUser(){
        User actualUser = userService.loginUser("zig@zag1.com","zigzag");

        Map<String, String> userDetails = new HashMap<>();
        userDetails.put(Constants.FIRST_NAME, "John ABC");
        userDetails.put(Constants.LAST_NAME, "Doe1");
        userDetails.put(Constants.CONTACT, "9096754412");
        User updatedUser = userService.updateUserDetails(actualUser.getEmail(), userDetails);

        //check if response is not null
        Assertions.assertNotNull(updatedUser);

        //Check if firstname,lastname and contact are updated
        Assertions.assertEquals("John ABC", updatedUser.getFirstName());
        Assertions.assertEquals("Doe1", updatedUser.getLastName());
        Assertions.assertEquals("zig@zag1.com", updatedUser.getEmail());
        Assertions.assertEquals("9096754412", updatedUser.getContact());

    }

}
