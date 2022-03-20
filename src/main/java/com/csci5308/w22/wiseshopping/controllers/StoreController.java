package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Store;

import com.csci5308.w22.wiseshopping.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

/**
 * @author Elizabeth James
 */

@Controller
@RequestMapping("store")
public class StoreController {


    @Autowired
    private StoreService storeService;

    @PostMapping("/addstore")
    public Store addStore(@Validated Store store, @Validated String startingTime, @Validated String endingTime){
        return storeService.addStore(store.getStoreName(), store.getType(), startingTime, endingTime ,store.getContact(), store.getMerchant(), store.getLocation());
    }

    @PostMapping("/removestore")
    public void removeStore(@Validated Store store){
        storeService.remove(store);
    }
}
