package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import com.csci5308.w22.wiseshopping.utils.Constants;
import com.csci5308.w22.wiseshopping.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Elizabeth James
 */
@Component
public class StoreScreen implements Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationScreen.class);
    private Scanner scanner;

    private StoreService storeService;
    private LocationService locationService;

    private ArrayList<String> validScreens;


//    private List<String> validScreens;

    private Merchant merchant;
    private User user;

    @Autowired
    public StoreScreen(Scanner scanner, StoreService storeService, LocationService locationService) {
        this.scanner = scanner;
        this.storeService = storeService;
        this.locationService = locationService;

//        validScreens= (ArrayList<String>) Arrays.asList("login", "dummy");
        validScreens= new ArrayList<>(Arrays.asList(Constants.MERCHANT_MENU, Constants.LOGOUT));
    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("****STORE MENU****");
        LOGGER.info("");
        boolean success = false;
        try {
            String input = "";
            LOGGER.info("enter one of the following - add or delete or update");
            input = scan(scanner);
            if (input.equals(Constants.ADD)) {
                LOGGER.info("Enter <store_name> <business_type> <start_time> <end_time> <contact> ");
                String storeName = scan(scanner);
                String businessType = scan(scanner);
                String startTime = scan(scanner);
                String endTime = scan(scanner);
                String contact = scan(scanner);
                LOGGER.info("Enter <locationName> <zipcode> <province> <country>");
                String locationName = scan(scanner);
                String zipcode = scan(scanner);
                String province = scan(scanner);
                String country = scan(scanner);
                Location location = locationService.addLocation(locationName, zipcode, province, country);
                Store store = storeService.addStore(storeName,businessType, startTime, endTime, contact,merchant,location);
                if (store!=null){
                    success = true;
                }

            } else if (input.equals(Constants.DELETE)) {
                List<Store> storeList = storeService.getAllStoresBelongingToAMerchant(merchant);
                storeList.stream().forEach(s -> LOGGER.info( "Store id:  {}, name: {}", s.getId(), s.getName()));
                LOGGER.info("Enter the id to be deleted");
                String idToBeDeleted = scan(scanner);
                success = storeService.remove(Integer.parseInt(idToBeDeleted));

            }
            else if (input.equals(Constants.UPDATE)){
                List<Store> storeList = storeService.getAllStoresBelongingToAMerchant(merchant);
                storeList.stream().forEach(s -> LOGGER.info( "Store id:  {}, name: {}", s.getId(), s.getName()));
                LOGGER.info("Enter the id to be deleted");
                int idToBeUpdated = Integer.parseInt(scan(scanner));
                Store storeToBeUpdated = storeService.getStoreById(idToBeUpdated);
                LOGGER.info("Pass the attributes to be updated. Enter none to skip the attribute.");
                String inputKey;
                Map<String,String> mapAttributes = new HashMap<>();
                mapAttributes.put(Constants.KEY_CONTACT,"");
                mapAttributes.put(Constants.KEY_END_TIME,"");
                mapAttributes.put(Constants.KEY_NAME,"");
                mapAttributes.put(Constants.KEY_START_TIME,"");
                mapAttributes.put(Constants.KEY_TYPE_OF_BUSINESS,"");
                for (Map.Entry<String, String> entry : mapAttributes.entrySet()){
                    LOGGER.info("Enter the value to be updated for {}", entry.getKey());
                    entry.setValue(scan(scanner));
                }
                Store updatedStore = storeService.updateStore(storeToBeUpdated,mapAttributes);
                success = updatedStore!=null;
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
