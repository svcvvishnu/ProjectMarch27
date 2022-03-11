package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.service.SubscriptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Elizabeth James
 */

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class SubscriptionServiceTests {

    @Mock
    private SubscriptionService subscriptionService;

    private static final double NEW_PRICE = 1.00;





    @Test
    public void testAlertSubscribers(){

        //TODO: replace this once productService is implemented
        Product product = new Product();
        product.setProductId(1);
        product.setProductName("Potatoes");

        Store store = new Store();
        store.setStoreId(1);
        store.setStoreName("walmart");
        Mockito.when(subscriptionService.alertSubscribers(product,store,(float) NEW_PRICE)).thenReturn(true);
        Assertions.assertTrue(subscriptionService.alertSubscribers(product, store, (float) NEW_PRICE));
    }
}
