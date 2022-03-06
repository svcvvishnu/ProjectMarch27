package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Elizabeth James
 */
@Controller
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/addlocation")
    public Location addLocation(@Validated Location location){
        return locationService.addLocation(location.getName(), location.getZipcode(), location.getProvince(), location.getCountry());
    }

    @PostMapping("/removelocation")
    public void removeLocation(@Validated Location location){
         locationService.remove(location);
    }
}
