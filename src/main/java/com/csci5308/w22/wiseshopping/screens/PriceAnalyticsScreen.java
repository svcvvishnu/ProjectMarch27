package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.CartService;
import com.csci5308.w22.wiseshopping.service.ProductService;
import com.csci5308.w22.wiseshopping.service.SalesService;
import com.csci5308.w22.wiseshopping.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.*;


/**
 * @author Pavithra Gunasekaran
 */
@Component
public class PriceAnalyticsScreen implements Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsScreen.class);

    private Scanner scanner;

    private SalesService salesService;

    private ArrayList<String> validScreens;

    private Merchant merchant;

    private User user;

    @Autowired
    public PriceAnalyticsScreen(Scanner scanner, SalesService salesService) {
        this.scanner = scanner;
        this.salesService = salesService;
        validScreens= new ArrayList<>(Arrays.asList(Constants.LOGOUT));
    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("****PRICE ANALYTICS****");
        LOGGER.info("");
        LOGGER.info("Enter a product name to get the price analytics by every month");
        boolean success = false;
        try {
            String input = "";
            input = scan(scanner);
            HashMap<Integer, Double> priceAnalytics = salesService.getProductLowestPriceAnalytics(input);
            boolean chartGenerated = salesService.generateChartForPriceAnalytics(input);

                for (Map.Entry<Integer, Double> entry : priceAnalytics.entrySet()) {
                    LOGGER.info(" Month : {} Price : {}",entry.getKey(),entry.getValue());

                }
            LOGGER.info("****************************************************************************");


        } catch (MenuInterruptedException e) {
            getNavigations(screenFactory, validScreens, LOGGER, scanner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public void setUser(User user) {
        this.user = user;

    }

}
