package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import com.csci5308.w22.wiseshopping.service.UserService;
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
public class UserMenuScreen implements Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuScreen.class);

    private Scanner scanner;

    private MerchantService merchantService;

    private UserService userService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private StoreService storeService;
    private List<String> validScreens;

//    private List<String> validScreens;

    private Merchant merchant;

    private User user;


    @Autowired
    public UserMenuScreen(Scanner scanner, MerchantService merchantService, UserService userService) {
        this.scanner = scanner;
        this.merchantService = merchantService;
        this.userService = userService;

//        validScreens= (ArrayList<String>) Arrays.asList("login", "dummy");
        validScreens = new ArrayList<>(Arrays.asList(Constants.LOGOUT));


    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("****USER MENU****");
        LOGGER.info("");
        boolean success = false;
        try {
            String input = "";

            do {
                LOGGER.info("Choose one of the following pages: products, subscriptions, profile, price_analytics");
                input = scan(scanner);
                if (input.equals(Constants.PRODUCTS)) {
                    Screen screen = screenFactory.getScreen(Constants.PRODUCTS);
                    screen.setUser(user);
                    success = screen.render(screenFactory);

                } else if (input.equals(Constants.PROFILE)) {
                    Screen screen = screenFactory.getScreen(Constants.PROFILE);
                    screen.setUser(user);
                    success = screen.render(screenFactory);
                } else if (input.equals(Constants.SUBSCRIPTIONS)) {
                    Screen screen = screenFactory.getScreen(Constants.SUBSCRIPTIONS);
                    screen.setUser(user);
                    success = screen.render(screenFactory);
                }
                else if (input.equals(Constants.PRICE_ANALYTICS)) {
                    Screen screen = screenFactory.getScreen(Constants.PRICE_ANALYTICS);
                    screen.setUser(user);
                    success = screen.render(screenFactory);
                }else if (input.equals("exit")) {
                    LOGGER.info("Exiting user menu...");
                } else {
                    LOGGER.warn("Invalid input received...");
                }
            } while (!input.equals("exit"));
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

