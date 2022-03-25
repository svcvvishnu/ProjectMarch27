package com.csci5308.w22.wiseshopping.integrationTests.screens;

import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.screens.LoginScreen;
import com.csci5308.w22.wiseshopping.screens.MerchantMenuScreen;
import com.csci5308.w22.wiseshopping.screens.StoreScreen;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.StoreService;
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

import java.util.List;
import java.util.Scanner;

/**
 * @author Elizabeth James
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MerchantScreenTests {

    @Mock
    private Scanner scanner;


    @Autowired
    private MerchantService merchantService;
    @Autowired
    private LocationService locationService;

    @Autowired
    private StoreService storeService;

    @InjectMocks
    @Autowired
    private LoginScreen loginScreen;

    @InjectMocks
    @Autowired
    private MerchantMenuScreen merchantMenuScreen;

    @Autowired
    private ScreenFactory screenFactory;

    private Merchant merchant;

    @BeforeEach
    public void setUp() {
        merchant = merchantService.getMerchantByEmail("zigzag@zigzag.com");
    }



    @Order(1)
    @Test
    public void testMerchantAddStore() {
        Mockito.when(scanner.next()).thenReturn("a_store")
                // store details
                .thenReturn("asdf", "s", "11", "12", "1234567")
                // location details
                .thenReturn("asf", "123dfg", "NS", "CA")
                //add another store
                .thenReturn("a_store")
                // store details
                .thenReturn("asdf1", "s", "11", "12", "1234567")
                // location details
                .thenReturn("asf", "123dfg", "NS", "CA")
                //add duplicate store
                .thenReturn("a_store")
                // store details
                .thenReturn("asdf1", "s", "11", "12", "1234567")
                // location details
                .thenReturn("asf", "123dfg", "NS", "CA")
                //exit
                .thenReturn("exit");
        merchantMenuScreen.setMerchant(merchant);
        Assertions.assertTrue(merchantMenuScreen.render(screenFactory));
    }

    @Order(3)
    @Test
    public void testMerchantDeleteStore() {
        List<Store> storeList = storeService.getAllStoresBelongingToAMerchant(merchant);

        Mockito.when(scanner.next()).thenReturn("d_store")
                // first store id
                .thenReturn(String.valueOf(storeList.get(0).getId()))
                .thenReturn("d_store")
                // second store id
                .thenReturn(String.valueOf(storeList.get(1).getId()))
                //exit
                .thenReturn("exit");
        merchantMenuScreen.setMerchant(merchant);
        Assertions.assertTrue(merchantMenuScreen.render(screenFactory));
    }

    @Order(2)
    @Test
    public void updateStoreTest(){
        List<Store> storeList = storeService.getAllStoresBelongingToAMerchant(merchant);

        Mockito.when(scanner.next()).thenReturn("u_store")
                // first store id
                .thenReturn(String.valueOf(storeList.get(0).getId()))
                // update attribute type
                .thenReturn("type:ghj")
                // update contact
                .thenReturn("contact:adsf")
                // invalid key
                .thenReturn("contact")
                // finish inputting key value pair
                .thenReturn("done")
                //exit
                .thenReturn("exit");
        merchantMenuScreen.setMerchant(merchant);
        Assertions.assertTrue(merchantMenuScreen.render(screenFactory));
    }


}
