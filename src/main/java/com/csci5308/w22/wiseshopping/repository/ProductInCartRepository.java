package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.service.ProductInCartService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pavithra Gunasekaran
 */
@Repository
public interface ProductInCartRepository extends CrudRepository<ProductInCart,Integer> {

    @Query(value="delete from product_in_cart where product_name = ?1",nativeQuery = true)
    Integer deleteByProductName(String productName);

    @Query(value = "SELECT * FROM product_in_cart as cart JOIN store as stores on cart.store_id=stores.store_id " +
            " where cart.cart_id = ?1 AND stores.location_id= ?2 AND stores.merchant_id= ?3", nativeQuery = true)
    List<ProductInCart> findByCartAndLocationAndMerchant(int cartId, int locationId, int merchantId);

    @Query(value = "SELECT * FROM product_in_cart as cart JOIN store as stores on cart.store_id=stores.store_id " +
            " where cart.cart_id = ?1 AND stores.merchant_id= ?2", nativeQuery = true)
    List<ProductInCart> findByCartAndMerchant(int cartId, int merchantId);

    @Query(value = "SELECT * FROM product_in_cart as cart JOIN store as stores on cart.store_id=stores.store_id " +
            " where cart.cart_id = ?1 AND stores.location_id= ?2", nativeQuery = true)
    List<ProductInCart> findByCartAndLocation(int cartId, int locationId);
}



