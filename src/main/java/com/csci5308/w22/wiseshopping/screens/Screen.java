package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.utils.Constants;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * @author Elizabeth James
 */
public interface Screen {

    void setMerchant(Merchant merchant);
    void setUser(User user);
    boolean render(ScreenFactory screenFactory);
    default boolean getNavigations(ScreenFactory screenFactory, List<String> validScreens, Logger logger, Scanner scanner) {
        logger.info("For additional navigation enter :<page>");
        String screens = "";
        validScreens.stream().forEach(e -> logger.info(e));
        String screen = null;
        try {
            screen = scan(scanner);
            while (!validScreens.contains(screen)){
                logger.error("Invalid screen. Please try again");
                screen = scan(scanner);
            }
            screenFactory.getScreen(screen).render(screenFactory);
        } catch (MenuInterruptedException e) {
            getNavigations(screenFactory,validScreens,logger,scanner);
        }
        return true;
    }

    default String scan (Scanner scanner) throws MenuInterruptedException {
        String s = scanner.next();
        if (s.equals(":")){
            throw new MenuInterruptedException("");
        }
        if (s.equals(Constants.QUIT)){
            System.exit(0);
        }
        return s;
    }

}
