package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;
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

import java.util.*;

/**
 * @author Elizabeth James
 */
@Component
public class MerchantMenuScreen implements Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationScreen.class);

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
    public MerchantMenuScreen(Scanner scanner, MerchantService merchantService, UserService userService) {
        this.scanner = scanner;
        this.merchantService = merchantService;
        this.userService = userService;

//        validScreens= (ArrayList<String>) Arrays.asList("login", "dummy");
        validScreens= new ArrayList<>(Arrays.asList(Constants.LOGOUT));


    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("****MERCHANT MENU****");
        LOGGER.info("");
        boolean success = false;
        try {
            String input = "";

            do {
                LOGGER.info("Choose one of the following");
                LOGGER.info("Use a_, u_, d_ to add, update or delete followed by store or product");
                LOGGER.info("eg: a_product, u_product, a_store, d_store, etc.");
                input = scan(scanner);
                if (input.equals("a_product")) {
                    LOGGER.info("TODO");
                } else if (input.equals("a_store")) {
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
                    Store store = storeService.addStore(storeName, businessType, startTime, endTime, contact, merchant, location);
                    if (store != null) {
                        success = true;
                    }
                } else if (input.equals("d_store")) {
                    List<Store> storeList = storeService.getAllStoresBelongingToAMerchant(merchant);
                    storeList.stream().forEach(s -> LOGGER.info("Store id:  {}, name: {}", s.getId(), s.getName()));
                    LOGGER.info("Enter the id to be deleted");
                    String idToBeDeleted = scan(scanner);
                    success = storeService.remove(Integer.parseInt(idToBeDeleted));
                } else if (input.equals("u_store")){
                    List<Store> storeList = storeService.getAllStoresBelongingToAMerchant(merchant);
                    storeList.stream().forEach(s -> LOGGER.info("Store id:  {}, name: {}", s.getId(), s.getName()));
                    LOGGER.info("Enter the id to be updated");
                    int idToBeUpdated = Integer.parseInt(scan(scanner));
                    Store storeToBeUpdated = storeService.getStoreById(idToBeUpdated);
                    LOGGER.info("Pass the attributes to be updated. Enter in <key>:<value> format");
                    LOGGER.info("Acceptable keys are : name, type, startTime, endTime, contact");
                    Map<String,String> mapAttributes = new HashMap<>();

                    String keyValuePair = scan(scanner);
                    do {
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
                    Store updatedStore = storeService.updateStore(storeToBeUpdated,mapAttributes);
                    success = updatedStore!=null;
                }
                else if (input.equals("exit")){
                    LOGGER.info("Exiting merchant menu...");
                }
                else {
                    LOGGER.warn("Invalid input received...");
                }
            } while (!input.equals("exit"));
        }
        catch(MenuInterruptedException e){
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
