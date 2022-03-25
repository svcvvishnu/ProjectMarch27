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
public class LoginScreen implements Screen{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginScreen.class);

//    private List<String> validScreens;

    private ArrayList<String> validScreens;


    private Scanner scanner;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;

    private Merchant merchant;

    private User user;


    public LoginScreen(Scanner scanner, MerchantService merchantService, UserService userService) {
        this.scanner = scanner;
        this.merchantService = merchantService;
        this.userService = userService;

        validScreens= new ArrayList<>(Arrays.asList(Constants.REGISTER));


    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("***LOGIN SCREEN****");
        LOGGER.info("use : for additional navigation");
        boolean success = false;
        try{
        String input = "";
        LOGGER.info("Are you a merchant or a user?");
        input = scan(scanner);

        if (Constants.MERCHANT.equalsIgnoreCase(input)){
            LOGGER.info("Enter <username> <password>");
            String username =scan(scanner);
            String password = scan(scanner);
            Merchant merchant = merchantService.loginMerchant(username, password);
            Screen screen = screenFactory.getScreen(Constants.MERCHANT_MENU);
            screen.setMerchant(merchant);
            success = screen.render(screenFactory);
        }
        if (Constants.USER.equalsIgnoreCase(input)){
            LOGGER.info("Enter <email> <password>");
            String email =scan(scanner);
            String password = scan(scanner);
            User user = userService.loginUser(email, password);
            Screen screen = screenFactory.getScreen(Constants.USER_MENU);
            screen.setUser(user);
            success = screen.render(screenFactory);
        }}
        catch (MenuInterruptedException e){
            getNavigations(screenFactory,validScreens,LOGGER,scanner);
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
