package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.Cart;
import com.csci5308.w22.wiseshopping.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Adarsh Kannan
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
    Cart findByUser(User currentUser);


}