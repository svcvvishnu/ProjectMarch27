package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.UserService;
import com.csci5308.w22.wiseshopping.utils.Constants;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
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
public class RegistrationScreen implements Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationScreen.class);

    private Scanner scanner;

    private ArrayList<String> validScreens;


    private MerchantService merchantService;

    private UserService userService;

    private Merchant merchant;
    private User user;


    @Autowired
    public RegistrationScreen(Scanner scanner, MerchantService merchantService, UserService userService) {
        this.scanner = scanner;
        this.merchantService = merchantService;
        this.userService = userService;
                validScreens= new ArrayList<>(Arrays.asList(Constants.LOGIN));

    }


    @Override
    public boolean render(ScreenFactory screenFactory) {
        boolean success = false;
        LOGGER.info("***REGISTRATION****");
        LOGGER.info("use : for additional navigation");
        try {
            String input = "";
            LOGGER.info("Are you a merchant or a user?");
            input = scan(scanner);

            if (Constants.MERCHANT.equalsIgnoreCase(input)) {
                LOGGER.info("Enter <name> <email> <password>");
                String name = scan(scanner);
                String email = scan(scanner);
                String password = scan(scanner);
                merchant = merchantService.registerMerchant(name, email, password);
                Screen screen = screenFactory.getScreen(Constants.MERCHANT_MENU);
                screen.setMerchant(merchant);
                success = screen.render(screenFactory);
            }
            if (Constants.USER.equalsIgnoreCase(input)) {
                LOGGER.info("Enter <first name> <last name> <email> <password> <contact>");
                String firstName = scan(scanner);
                String secondName = scan(scanner);
                String email = scan(scanner);
                String password = scan(scanner);
                String contact = scan(scanner);
                User user = userService.registerUser(firstName, secondName, email, password,contact);
                success = user!=null;
            }

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
