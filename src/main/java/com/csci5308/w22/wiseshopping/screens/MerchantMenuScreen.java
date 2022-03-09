package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.MerchantService;
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
public class MerchantMenuScreen implements Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationScreen.class);

    private Scanner scanner;

    private MerchantService merchantService;

    private UserService userService;

    private ArrayList<String> validScreens;

//    private List<String> validScreens;

    private Merchant merchant;

    private User user;


    @Autowired
    public MerchantMenuScreen(Scanner scanner, MerchantService merchantService, UserService userService) {
        this.scanner = scanner;
        this.merchantService = merchantService;
        this.userService = userService;
//        validScreens= (ArrayList<String>) Arrays.asList("login", "dummy");
        validScreens= new ArrayList<>(Arrays.asList("login", "dummy"));

    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("****MERCHANT MENU****");
        LOGGER.info("");
        boolean success = false;
        try {
            String input = "";
            LOGGER.info("Choose one of the following");

            LOGGER.info("1. edit store details");
            LOGGER.info("2. edit product details");
            input = scan(scanner);
            if(input.equals(Constants.OPTION_ONE)){
                Screen screen = screenFactory.getScreen( Constants.STORE_MENU);
                screen.setMerchant(merchant);
                success = screen.render(screenFactory);
            }
            else if(input.equals(Constants.OPTION_TWO)){
                //TODO
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
