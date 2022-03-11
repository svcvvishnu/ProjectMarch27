package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.LocationRepository;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Scanner;

import static org.mockito.Mockito.when;

/**
 * @author Elizabeth James
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@ExtendWith(MockitoExtension.class)
public class LoginScreenTests {

    @Mock
    private Scanner scanner;

    @Autowired
    @InjectMocks
    private LoginScreen loginScreen;
    @Autowired
    private ScreenFactory screenFactory;


    //@Test
    public void testLoginForMerchant(){
        Assertions.assertTrue(true);
        when(scanner.next()).thenReturn("merchant").thenReturn("zil").thenReturn("zil@zil.com").thenReturn("zil");
        Assertions.assertTrue(loginScreen.render(screenFactory));
    }

//    @Test
    public void testLoginForUser(){
        Assertions.assertTrue(true);
        when(scanner.next()).thenReturn("user").thenReturn("zil@zil.com").thenReturn("zil");
        Assertions.assertTrue(loginScreen.render(screenFactory));
    }
}
