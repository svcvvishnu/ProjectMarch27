package com.csci5308.w22.wiseshopping.integrationTests.screens;

import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.screens.LoginScreen;
import com.csci5308.w22.wiseshopping.screens.MerchantMenuScreen;
import com.csci5308.w22.wiseshopping.screens.RegistrationScreen;
import com.csci5308.w22.wiseshopping.service.MerchantService;
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
public class LoginScreenTests {
    @Mock
    private Scanner scanner;


    @Autowired
    private MerchantService merchantService;

    @InjectMocks
    @Autowired
    private LoginScreen loginScreen;

    @Autowired
    @InjectMocks
    private ScreenFactory screenFactory;

    private UserService userService;

    private User user;

    private Merchant merchant;

    @InjectMocks
    private MerchantMenuScreen merchantMenuScreen;

    @Test
    public void testUserLoginNegative(){
        Mockito.when(scanner.next()).thenReturn("user")
                //email
                .thenReturn("zig@zag.com")
                //password
                .thenReturn("zigzag1");
        Assertions.assertFalse(loginScreen.render(screenFactory));

    }
    @Test
    public void testMerchantLoginNegative(){
        Mockito.when(scanner.next()).thenReturn("merchant")
                //email
                .thenReturn("zigzag@zigzag.com")
                //password
                .thenReturn("zigzag1");
        Assertions.assertFalse(loginScreen.render(screenFactory));

    }

    // positive login test case is included in the further screens as mocked scanner cannot be inject in the further inner classes
}
