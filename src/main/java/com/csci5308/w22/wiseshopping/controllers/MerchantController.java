package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Elizabeth James
 */
@Controller
@RequestMapping("merchant")
public class MerchantController {


    @Autowired
    SessionService sessionService;

    @Autowired
    private MerchantService merchantService;


    @GetMapping ("/registerMerchant")
    public String getRegistrationForm(@ModelAttribute("merchant") Merchant merchant) {
        return "merchant/registerMerchant";
    }

    @PostMapping("/registerMerchant")
    public String registerMerchant (@ModelAttribute("merchant") Merchant merchant, HttpServletRequest request, HttpServletResponse response){
        merchant = merchantService.registerMerchant(merchant.getName(), merchant.getEmail(),merchant.getPassword());
        sessionService.setSession(merchant.getId(), request, response );
        return "redirect:/store/addStore";
    }

    @PostMapping("/login")
    //TODO: set http as only cookies
    public String login(@Validated Merchant merchant){
        merchantService.loginMerchant(merchant.getEmail(), merchant.getPassword());
        return "index";
    }

}
