package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.service.ProductInventoryService;
import com.csci5308.w22.wiseshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Pavithra Gunasekaran
 */
@Controller
@RequestMapping("product")
public class ProductInventoryController {

    @Autowired
    private ProductInventoryService productInventoryService;

    @GetMapping("/productStock")
    public String getProducts(Model model) {
        model.addAttribute("products", productInventoryService.getProducts());
        //productInventoryService.getProducts();
        return "Products";
    }


    @RequestMapping("/addProducts")
    public String addProductsToCart(@RequestParam(value = "productName", required = false) String productName,
                                    @RequestParam(value = "storeName", required = false) String storeName,
                                    @RequestParam(value = "productPrice", required = false) Integer productPrice,
                                    @RequestParam(value = "productQuantity", required = false) Integer productQuantity,
                                    Model model){


        return  "Products";

    }

}
