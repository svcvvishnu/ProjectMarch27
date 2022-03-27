package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import com.csci5308.w22.wiseshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Elizabeth James
 */
@Controller
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    //TODO: set http as only cookies
    public String login(@Validated User user){
        userService.loginUser(user.getEmail(), user.getPassword());
        return "index";
    }

    @GetMapping ("/registerUser")
    public String getRegistrationForm(@ModelAttribute("user") User user) {
        return "user/registerUser";
    }

    @PostMapping("/registerUser")
    public String registerUser (@ModelAttribute("user") User user){
        userService.registerUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getContact(),user.getSecurity_code());
        return "index";
    }
}
