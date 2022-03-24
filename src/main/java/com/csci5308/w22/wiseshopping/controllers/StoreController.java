package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;

import com.csci5308.w22.wiseshopping.repository.LocationRepository;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Elizabeth James
 */

@Controller
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private MerchantService merchantService;

    @Autowired private LocationRepository locationRepository;

    @GetMapping ("/addStore")
    public String getAddStoreForm(Model model) {
        model.addAttribute("store", new Store());

        Iterable<Location> locations =  locationRepository.findAll();
        model.addAttribute("locations", locations);
        return "store/addStore";
    }

    @PostMapping("/addStore")
    public String addStore (@ModelAttribute("store") Store store, HttpServletRequest request, HttpServletResponse response){
        storeService.addStore(store.getName(), store.getType(), store.getStartTime().toString(), store.getEndTime().toString() ,store.getContact(), merchantService.getMerchantFromSession(request), store.getLocation());
        return "index";
    }
//    @PostMapping("/addstore")
//    public Store addStore(@Validated Store store, @Validated String startingTime, @Validated String endingTime){
//        return storeService.addStore(store.getName(), store.getType(), startingTime, endingTime ,store.getContact(), store.getMerchant(), store.getLocation());
//    }

    @PostMapping("/removestore")
    public void removeStore(@Validated Store store){
        storeService.remove(store);
    }
}
