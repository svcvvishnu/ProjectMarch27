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


    @GetMapping("/register")
    public String register(Merchant merchant){
        return "registerMerchant";
    }

    @PostMapping("/addMerchant")
    public String registerMerchant(@Validated Merchant merchant) {
        merchantService.registerMerchant(merchant.getMerchantName(), merchant.getEmail(), merchant.getPassword());
        return "index";
      }

    @PostMapping("/login")
    //TODO: set http as only cookies
    public String login(@Validated Merchant merchant){
        merchantService.loginMerchant(merchant.getEmail(), merchant.getPassword());
        return "index";
    }

}
