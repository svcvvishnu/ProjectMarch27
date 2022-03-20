package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Elizabeth James
 */
@Controller
@RequestMapping("merchant")
public class MerchantController {


    @Autowired
    private MerchantService merchantService;


    @GetMapping ("/registerMerchant")
    public String getRegistrationForm(@ModelAttribute("merchant") Merchant merchant) {
        return "merchant/registerMerchant";
    }

    @PostMapping("/registerMerchant")
    public String registerMerchant (@ModelAttribute("merchant") Merchant merchant){
        merchantService.registerMerchant(merchant.getName(), merchant.getEmail(),merchant.getPassword());
        return "index";
    }

    @PostMapping("/login")
    //TODO: set http as only cookies
    public String login(@Validated Merchant merchant){
        merchantService.loginMerchant(merchant.getEmail(), merchant.getPassword());
        return "index";
    }

}
