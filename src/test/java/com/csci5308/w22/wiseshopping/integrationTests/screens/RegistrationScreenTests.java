package com.csci5308.w22.wiseshopping.integrationTests.screens;

import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.screens.LoginScreen;
import com.csci5308.w22.wiseshopping.screens.MerchantMenuScreen;
import com.csci5308.w22.wiseshopping.screens.RegistrationScreen;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import com.csci5308.w22.wiseshopping.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Scanner;

/**
 * @author Elizabeth James
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationScreenTests {
    @Mock
    private Scanner scanner;


    @Autowired
    private MerchantService merchantService;

    @InjectMocks
    @Autowired
    private RegistrationScreen registrationScreen;

    @Autowired
    private ScreenFactory screenFactory;

    private UserService userService;

    private User user;

    private Merchant merchant;

    @Test
    public void testUserRegistration(){
        Mockito.when(scanner.next()).thenReturn("user")
                // first name
                .thenReturn("zig")
                //last name
        .thenReturn("zag")
                //email
        .thenReturn("zig@zag1.com")
                //password
        .thenReturn("zigzag")
                //contact
        .thenReturn("123456");
        // user already registered exception is thrown
        Assertions.assertFalse(registrationScreen.render(screenFactory));

    }
    @Test
    public void testMerchantRegistration(){
        Mockito.when(scanner.next()).thenReturn("merchant")
                // first name
                .thenReturn("zig")
                //email
                .thenReturn("zig@zag.com")
                //password
                .thenReturn("zigzag");
        // user already registered exception is thrown
        Assertions.assertFalse(registrationScreen.render(screenFactory));

    }
}
