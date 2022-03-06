package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.screens.StoreScreen;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Elizabeth James
 */

@RestController
@RequestMapping("store")
public class StoreController {


    @Autowired
    private StoreService storeService;

    @PostMapping("/addstore")
    public Store addStore(@RequestBody Store store, @RequestParam String startingTime, @RequestParam String endingTime){
        return storeService.addStore(store.getStoreName(), store.getType(), startingTime, endingTime ,store.getContact(), store.getMerchant(), store.getLocation());
    }

    @PostMapping("/removestore")
    //TODO: set http as only cookies
    public void removeStore(@RequestBody Store store){
        storeService.remove(store);
    }
}
