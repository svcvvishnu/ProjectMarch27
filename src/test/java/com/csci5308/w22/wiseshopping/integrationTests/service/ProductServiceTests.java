package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.models.Sales;
import com.csci5308.w22.wiseshopping.service.ProductService;
import com.csci5308.w22.wiseshopping.service.SalesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Pavithra Gunasekaran
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class ProductServiceTests {
    @Autowired
    ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp(){
        product = new Product("Wired Headphones");
    }

    @Test
    public void testGetProductStockAvailability() throws SQLException {
        List<Product> productList = productService.getProductStockAvailability("milk","halifax");
        Assertions.assertTrue(productList.size()>0);

    }

}
