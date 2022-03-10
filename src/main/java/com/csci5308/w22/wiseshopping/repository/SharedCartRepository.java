package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.Cart;
import com.csci5308.w22.wiseshopping.models.SharedCart;
import com.csci5308.w22.wiseshopping.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SharedCartRepository extends CrudRepository<SharedCart, Integer> {

    SharedCart findByUserAndCart(User user, Cart cart);
}