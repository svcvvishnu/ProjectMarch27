package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.Subscription;
import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.service.ProductService;
import com.csci5308.w22.wiseshopping.service.SubscriptionService;
import com.csci5308.w22.wiseshopping.repository.ProductInventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Elizabeth James
 */

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class SubscriptionServiceTests {

    @Autowired
    private SubscriptionService subscriptionService;





    @Test
    public void testAlertSubscribers(){

        //TODO: replace this once productService is implemented
        Product product = new Product();
        product.setProductId(1);

        subscriptionService.alertSubscribers(product, (float)1.00);
    }
}
