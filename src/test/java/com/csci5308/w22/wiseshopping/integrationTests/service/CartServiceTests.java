package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import com.csci5308.w22.wiseshopping.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adarsh Kannan
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class CartServiceTests {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testShareCart(){
        User currentUser = userRepository.findById(1).orElse(null);
        List<User> otherUsers = new ArrayList<>();
        otherUsers.add(userRepository.findById(2).orElse(null));
        otherUsers.add(currentUser);
        Assertions.assertNotNull(cartService.shareCart(currentUser,otherUsers));
    }
}