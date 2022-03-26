package com.csci5308.w22.wiseshopping.integrationTests.screens;

import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.screens.LoginScreen;
import com.csci5308.w22.wiseshopping.screens.RegistrationScreen;
import com.csci5308.w22.wiseshopping.screens.UserProfileScreen;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.UserService;
import org.junit.jupiter.api.*;
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
public class UserProfileScreenTest {
    @Mock
    private Scanner scanner;


    @Autowired
    private UserService merchantService;

    @InjectMocks
    @Autowired
    private UserProfileScreen userProfileScreen;

    @Autowired

    private ScreenFactory screenFactory;

    @Autowired
    @InjectMocks
    private UserProfileScreen screen;

    private User user;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        user = userService.loginUser("zig@zag1.com","zigzag");
    }


    @Test
    public void testViewUserProfile(){

        Mockito.when(scanner.next())
                .thenReturn("e_profile").thenReturn("contact:123").thenReturn("done").thenReturn("exit");
        userProfileScreen.setUser(user);
        Assertions.assertTrue(userProfileScreen.render(screenFactory));

    }
}
