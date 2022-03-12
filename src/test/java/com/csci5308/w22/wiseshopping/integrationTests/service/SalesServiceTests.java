package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.Sales;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.SalesService;
import com.google.common.collect.Multimap;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Pavithra Gunasekaran
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SalesServiceTests {
    @Autowired
    private SalesService salesService;

    private Sales sales;

    @BeforeEach
    public void setUp(){
        sales = new Sales("Wired Headphones");
    }



    @Test
    public void testGetProductLowestPriceAnalytics() throws SQLException {
       // Multimap<Integer, List<Double>> actualSalesData = salesService.getProductLowestPriceAnalytics("Wired Headphones");
        HashMap<Integer, Double> sales = salesService.getProductLowestPriceAnalytics("Wired Headphones");
        for (Map.Entry<Integer, Double> entry : sales.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        Assertions.assertTrue(sales.size()>0);

    }
}
