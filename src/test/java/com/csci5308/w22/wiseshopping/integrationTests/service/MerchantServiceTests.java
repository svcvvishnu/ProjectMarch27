package com.csci5308.w22.wiseshopping.integrationTests.service;
import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.models.Tags;
import com.csci5308.w22.wiseshopping.repository.*;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import net.bytebuddy.build.Plugin;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import java.util.List;
/**
 * @author Elizabeth James
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MerchantServiceTests {
    @Autowired
    private MerchantService merchantService;

    private Merchant merchant;

    @BeforeEach
    public void setUp(){
        merchant = new Merchant("John Doe", "johndoe@xyz.com", "password123");
    }

    @AfterAll()
    public void cleanUp(){
        merchantService.removeMerchant("johndoe@xyz.com");
    }
    @Test
    @Order(1)
    public void testRegisterMerchant(){
        Merchant actualMerchant = merchantService.registerMerchant("John Doe","johndoe@xyz.com","password123");
        merchant.setMerchantId(actualMerchant.getMerchantId());
        Assertions.assertEquals(merchant,actualMerchant);

    }

    @Test
    @Order(2)
    public void testLoginMerchant(){
        Merchant actualMerchant = merchantService.loginMerchant("johndoe@xyz.com","password123");
        Assertions.assertEquals(merchant.getPassword(),actualMerchant.getPassword());
    }


    @Test
    @Order(3)
    public void testRemoveExistingMerchant(){
        merchantService.registerMerchant("John Doe2","johndoe2@xyz.com","password123");
        Assertions.assertTrue(merchantService.removeMerchant("johndoe2@xyz.com"));
    }

    @Test
    @Order(4)
    public void testRemoveNonExistingMerchant(){
        Assertions.assertFalse(merchantService.removeMerchant("johndoe2@xyz.com"));
    }




}
