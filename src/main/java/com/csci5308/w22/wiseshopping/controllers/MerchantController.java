package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import com.csci5308.w22.wiseshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Elizabeth James
 */
@RestController
@RequestMapping("merchant")
public class MerchantController {


    @Autowired
    private MerchantService merchantService;



    @PostMapping("/register")
    public Merchant register(@RequestBody Merchant merchant){
        return merchantService.registerMerchant(merchant.getMerchantName(), merchant.getEmail(), merchant.getPassword());
    }

    @PostMapping("/login")
    //TODO: set http as only cookies
    public Merchant login(@RequestBody Merchant merchant){
        return merchantService.loginMerchant(merchant.getEmail(), merchant.getPassword());
    }

}
