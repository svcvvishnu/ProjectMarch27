package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Cart;
import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.ProductInCart;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.repository.ProductInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Pavithra Gunasekaran
 */
@Service
public class ProductInCartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);

    @Autowired
    ProductInCartRepository productInCartRepository;

    @Transactional
    public ProductInCart addProductInCart(Cart cart, Product product, Store store, int quantity, int price){


        if (cart ==  null){
            throw new IllegalArgumentException("cart id cannot be null or empty or blank");
        }

        if (product == null ){
            throw new IllegalArgumentException("product id cannot be null or empty or blank");
        }

        if (store == null){
            throw new IllegalArgumentException("store id cannot be null or empty or blank");
        }

        if (quantity==0 ){
            throw new IllegalArgumentException("quantity cannot be zero");
        }

        if (price ==0){
            throw new IllegalArgumentException("price cannot be zero");
        }
       ProductInCart productInCart=new ProductInCart(cart,product,store,quantity,price);
        productInCartRepository.save(productInCart);
        LOGGER.info("Product {} is added to cart{}",product.getProductId(),cart.getId());
        return productInCart;
    }

    @Transactional
    public boolean removeProductInCart(ProductInCart productInCart) {
        if (productInCart == null){
            throw new IllegalArgumentException("product cannot be null");
        }
        productInCartRepository.delete(productInCart);
        return true;
    }

    @Transactional
    public boolean remove(ProductInCart productInCart) {
        if (productInCart == null){
            throw new IllegalArgumentException("product cannot be null");
        }
        productInCartRepository.delete(productInCart);
        return true;
    }
}
