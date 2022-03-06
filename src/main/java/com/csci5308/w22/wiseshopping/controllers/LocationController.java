package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.service.LocationService;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Elizabeth James
 */
@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/addlocation")
    public Location addLocation(@RequestBody Location location){
        return locationService.addLocation(location.getName(), location.getZipcode(), location.getProvince(), location.getCountry());
    }

    @PostMapping("/removelocation")
    //TODO: set http as only cookies
    public void removeLocation(@RequestBody Location location){
         locationService.remove(location);
    }
}
