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
public class LogOutScreen implements Screen{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginScreen.class);

    private List<String> validScreens;

    private Scanner scanner;

    private MerchantService merchantService;

    private UserService userService;

    private Merchant merchant;

    private User user;

    @Autowired
    public LogOutScreen(Scanner scanner, MerchantService merchantService, UserService userService) {
        this.scanner = scanner;
        this.merchantService = merchantService;
        this.userService = userService;
//        validScreens = List.of(Constants.REGISTER, Constants.LOGIN);

        validScreens= new ArrayList<>(Arrays.asList(Constants.REGISTER, Constants.LOGIN));
    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        boolean success = false;
        LOGGER.info("***LOGGED OUT****");
        Screen screen = screenFactory.getScreen( Constants.LOGIN);
        success = screen.render(screenFactory);
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
