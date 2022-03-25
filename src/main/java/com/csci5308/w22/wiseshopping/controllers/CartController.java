package com.csci5308.w22.wiseshopping.controllers;

import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.repository.CartRepository;
import com.csci5308.w22.wiseshopping.repository.ProductInCartRepository;
import com.csci5308.w22.wiseshopping.repository.ProductRepository;
import com.csci5308.w22.wiseshopping.repository.StoreRepository;
import com.csci5308.w22.wiseshopping.service.CartService;
import com.csci5308.w22.wiseshopping.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author Pavithra Gunasekaran
 */
@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductInCartRepository productInCartRepository;

    @Autowired
    private StoreRepository storeRepository;

//    @RequestMapping(value="/createCart")

//    public String createCart(@ModelAttribute("productCart")ProductInCart productInCart){
//        Random random=new Random();
//        int cartUpperBound=100000;
//        int productInCartUpperBound=100000;
//        int cart_id=random.nextInt(cartUpperBound);
//        int product_in_cart_id=random.nextInt(productInCartUpperBound);
//        User userLogged=new User(1);
//        Cart cart=new Cart(cart_id, userLogged,"inprogress");
//
//        List<Product> products= productRepository.findByProductName(productInCart.getProduct().getProductName());
//        System.out.println(products.get(0).getProductId());
//        Product product = new Product(
//                products.get(0).getProductId(),
//                products.get(0).getProductName(),
//                products.get(0).getProductDescription());
//        Store stores = storeRepository.findByStoreName(productInCart.getStore().getStoreName());
//        Store store = new Store(stores.getStoreId());
//        ProductInCart productsInCart=new ProductInCart(product_in_cart_id,cart,product,store,productInCart.getQuantity(),productInCart.getPrice());
//        cartRepository.save(cart);
//        productInCartRepository.save(productsInCart);
//        return "CartSuccess";
//
//    }
}
