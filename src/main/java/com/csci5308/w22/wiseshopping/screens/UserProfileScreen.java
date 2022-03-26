package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import com.csci5308.w22.wiseshopping.service.UserService;
import com.csci5308.w22.wiseshopping.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Elizabeth James
 */

@Component
public class UserProfileScreen implements Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMenuScreen.class);

    private Scanner scanner;



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
    public UserProfileScreen(Scanner scanner,  UserService userService) {
        this.scanner = scanner;
        this.userService = userService;

//        validScreens= (ArrayList<String>) Arrays.asList("login", "dummy");
        validScreens = new ArrayList<>(Arrays.asList(Constants.LOGOUT));


    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("****USER PROFILE SCREEN****");
        LOGGER.info("");
        LOGGER.info("Choose one of the following");
        LOGGER.info("Use v_, u_, to view or update followed by profile");
        LOGGER.info("eg: v_profile, u_profile");

        boolean success = false;
        try {
            String input = "";
            do{
                input = scan(scanner);
                if (input.equals("e_profile")) {
                    LOGGER.info("Your profile:");
                    LOGGER.info("First Name: {}, Last Name: {}, emailId: {}, Contact Number: {}",user.getFirstName(), user.getLastName(), user.getEmail(), user.getContact() );
                    Map<String,String> mapAttributes = new HashMap<>();
                    String keyValuePair = scan(scanner);
                    do {
                        LOGGER.info("Pass the attributes to be updated. Enter in <key>:<value> format");
                        LOGGER.info("Acceptable keys are : firstName, lastName, contact");
                        try {
                            String[] pair = keyValuePair.split(":");
                            mapAttributes.put(pair[0], pair[1]);

                        }
                        catch (Exception e){
                            LOGGER.error("Invalid key value pair: {}", keyValuePair);
                        }
                        finally {
                            keyValuePair = scan(scanner);
                        }
                    }while (!keyValuePair.equals("done"));
                    User user1 = userService.updateUserDetails(user.getEmail(),mapAttributes);
                    System.out.println(mapAttributes);
                    success = user1!=null;
                }
                else if (input.equals("exit")){
                    LOGGER.info("Exiting user profile menu...");
                }
                else {
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

