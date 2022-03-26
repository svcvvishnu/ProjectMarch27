package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.ProductInCart;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.service.ProductInCartService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Pavithra Gunasekaran
 */
@Repository
public interface ProductInCartRepository extends CrudRepository<ProductInCart,Integer> {

    @Query(value="delete from product_in_cart where product_name = ?1",nativeQuery = true)
    Integer deleteByProductName(String productName);
    }



