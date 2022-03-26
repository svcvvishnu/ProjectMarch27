package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.exceptions.NoCartCreatedForUserException;
import com.csci5308.w22.wiseshopping.models.Cart;
import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.SharedCart;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.CartRepository;
import com.csci5308.w22.wiseshopping.repository.SharedCartRepository;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import com.csci5308.w22.wiseshopping.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Adarsh Kannan
 */
@AllArgsConstructor
@NoArgsConstructor
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private SharedCartRepository sharedCartRepository;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public Cart shareCart( User currentUser, List<User> otherUsersToShare){
        Cart cart = cartRepository.findByUser(currentUser);
        if (cart == null){
            throw new NoCartCreatedForUserException("no cart for user " + currentUser);
        }
        cart.setStatus(Constants.SHARED);
        cartRepository.save(cart);
        for (User user: otherUsersToShare){
            SharedCart sharedCart = sharedCartRepository.findByUserAndCart(user,cart);
            if (sharedCart == null) {
                sharedCart = new SharedCart(user, cart);
                sharedCartRepository.save(sharedCart);
            }
        }
        return cart;
    }

    @Transactional
    public Cart addCart( User user, String status ){
        //User userLogged=new User(1);
        Cart cart = new Cart(user,status);
        cartRepository.save(cart);
        System.out.println("Cart added "+cart.getId());

        return cart;
    }

    @Transactional
    public boolean remove(Cart cart) {
        if (cart == null){
            throw new IllegalArgumentException("cart cannot be null");
        }
        cartRepository.delete(cart);
        return true;
    }
}