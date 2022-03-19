package com.csci5308.w22.wiseshopping.controllers;

import java.util.*;

import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.models.UserInterface;
import com.csci5308.w22.wiseshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
 @RequestMapping ("register/")

public class UserInterfaceController
{
    @Autowired
    private UserService userService;

    @RequestMapping ("/NewUserRegistration")
    public String showForm() {
//        model.addAttribute("registerUser",
//                userService.registerUser(registerUser().FirstName, registerUser().LastName, registerUser().InputEmail, registerUser().InputPassword,registerUser().Contact));
 //       User newUser = userService.registerUser(user.getUserFirstName(),user.getUserLastName(),user.getEmail(),user.getPassword(),user.getContact());
        return "registerUser";
    }

    @PostMapping("/register1")
    public String submitForm(@ModelAttribute("userInterface") UserInterface userInterface) {
        System.out.println(userInterface);
        return "register_success";
    }
}
