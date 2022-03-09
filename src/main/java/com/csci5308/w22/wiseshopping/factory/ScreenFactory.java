package com.csci5308.w22.wiseshopping.factory;

import com.csci5308.w22.wiseshopping.exceptions.InvalidScreenException;
import com.csci5308.w22.wiseshopping.screens.*;
import com.csci5308.w22.wiseshopping.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Elizabeth James
 */
@Service
public class ScreenFactory {

    @Autowired
    private LoginScreen loginScreen;
    @Autowired
    private RegistrationScreen registrationScreen;
    @Autowired
    private StoreScreen storeScreen;
    @Autowired
    private MerchantMenuScreen merchantMenu;
    @Autowired
    private MerchantMenuScreen userupdateMenu;

    public Screen getScreen(String screen){
        switch (screen){
            case Constants.LOGIN: return loginScreen;
            case Constants.REGISTER: return registrationScreen;
            case Constants.STORE_MENU: return storeScreen;
            case Constants.MERCHANT: return merchantMenu;
            default: throw new InvalidScreenException("No such screen");
        }
    }


}
