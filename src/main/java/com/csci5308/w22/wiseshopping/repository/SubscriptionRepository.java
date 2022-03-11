package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.Subscription;
import com.csci5308.w22.wiseshopping.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Elizabeth James
 */
@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    //@Query(value = "SELECT * FROM subscription where user_id = ?1 product_id = ?2",nativeQuery = true)
    Subscription findByUserAndProduct(User user, Product product);

    @Query(value = "SELECT * FROM subscription where product_id = ?1",nativeQuery = true)
    List<Subscription> findByProductId (int productId);
}

