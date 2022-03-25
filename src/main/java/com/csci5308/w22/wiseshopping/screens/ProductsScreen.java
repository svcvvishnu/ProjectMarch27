package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.CartService;
import com.csci5308.w22.wiseshopping.service.ProductService;
import com.csci5308.w22.wiseshopping.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Elizabeth James
 */
@Component
public class ProductsScreen implements Screen{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsScreen.class);

    private Scanner scanner;

    private ProductService productService;

    private ArrayList<String> validScreens;

    private CartService cartService;

    private Merchant merchant;

    private User user;


    @Autowired
    public ProductsScreen(Scanner scanner, ProductService productService) {
        this.scanner = scanner;
        this.productService = productService;
        validScreens= new ArrayList<>(Arrays.asList(Constants.LOGOUT));
    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("****LIST OF PRODUCTS****");
        LOGGER.info("");
        boolean success = false;
        try {
            String input = "";
            List<ProductInventory> products = productService.getAvailableProducts();
            for (ProductInventory p : products){
                LOGGER.info("Product Name: {}", p.getProduct().getProductName());
                LOGGER.info("Id: {}", p.getProduct().getProductId());
                LOGGER.info("Available in: {} - {},{}", p.getStore().getName(), p.getStore().getLocation().getName(), p.getStore().getLocation().getZipcode());
                LOGGER.info("****************************************************************************");
            }

            // TODO: add products to cart here


        } catch (MenuInterruptedException e) {
            getNavigations(screenFactory, validScreens, LOGGER, scanner);
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
