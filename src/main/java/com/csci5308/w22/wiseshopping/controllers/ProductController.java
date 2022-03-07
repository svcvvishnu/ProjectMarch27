package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Elizabeth James
 */
@Controller
@RequestMapping("product/")
public class ProductController {
    @Autowired
    private MerchantService merchantService;

    @PostMapping("/updateProductPrice")
    public String registerMerchant(@Validated Product product, @Validated Store store, int price) {
        merchantService.updateProductPrice(product, store,price);
        return "index";
    }

}
