package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.StoreRepository;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import com.csci5308.w22.wiseshopping.service.UserService;
import com.csci5308.w22.wiseshopping.utils.Constants;
import jdk.javadoc.doclet.Taglet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Elizabeth James
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@ExtendWith(MockitoExtension.class)
public class RegistrationScreenTests {

    @Mock
    private Scanner scanner;

    @Autowired
    @InjectMocks
    private RegistrationScreen registrationScreen;
    @Autowired
    private ScreenFactory screenFactory;




    //TODO : uncomment these tests when registerUser and loginMerchant is implemented
    //@Test
    public void testRenderForMerchantAddStore() {
        when(scanner.next()).thenReturn("merchant").thenReturn("zil").thenReturn("zil@zil.com").thenReturn("zil").thenReturn("1").thenReturn("add").thenReturn("s").thenReturn("s").thenReturn("11").thenReturn("11").thenReturn("s").thenReturn("d").thenReturn("d").thenReturn("d").thenReturn("d");
        Assertions.assertTrue(registrationScreen.render(screenFactory));
    }

    //@Test
    public void testRenderForMerchantDeleteStore() {
        when(scanner.next()).thenReturn("merchant").thenReturn("zil").thenReturn("zil@zil.com").thenReturn("zil").thenReturn("1").thenReturn("delete").thenReturn("12");
        Assertions.assertTrue(registrationScreen.render(screenFactory));
    }

    //@Test
    public void testRenderForUser() {
        when(scanner.next()).thenReturn("user").thenReturn("zil").thenReturn("zil@zil.com").thenReturn("zil");
        //TODO : write scanner inputs post implementation
        Assertions.assertTrue(registrationScreen.render(screenFactory));

    }


}
