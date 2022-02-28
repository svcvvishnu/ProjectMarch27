package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import net.bytebuddy.build.Plugin;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Elizabeth James
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class MerchantServiceTests {
    @Autowired
    private MerchantService merchantService;

    private Merchant merchant;

    @BeforeEach
    public void setUp(){
        merchant = new Merchant("John Doe", "johndoe@xyz.com", "password123");
    }

    @AfterEach()
    public void cleanUp(){
        merchantService.removeMerchant("johndoe@xyz.com");
    }
    @Test
    public void testRegisterMerchant(){
        Merchant actualMerchant = merchantService.registerMerchant("John Doe","johndoe@xyz.com","password123");
        merchant.setMerchantId(actualMerchant.getMerchantId());
        Assertions.assertEquals(merchant,actualMerchant);

    }
    @Test
    public void testLoginMerchant(){
        Merchant actualMerchant = merchantService.loginMerchant("johndoe@xyz.com","password123");
        Assertions.assertEquals(merchant.getPassword(),actualMerchant.getPassword());

    }
    @Test
    public void testRemoveExistingMerchant(){
        merchantService.registerMerchant("John Doe","johndoe@xyz.com","password123");
        Assertions.assertTrue(merchantService.removeMerchant("johndoe@xyz.com"));
    }

    @Test
    public void testRemoveNonExistingMerchant(){
        Assertions.assertFalse(merchantService.removeMerchant("johndoe2@xyz.com"));
    }




}
