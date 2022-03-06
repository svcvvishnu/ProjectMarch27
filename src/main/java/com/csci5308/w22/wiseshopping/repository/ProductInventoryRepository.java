package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.models.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nilesh
 */
@Repository
public interface ProductInventoryRepository extends CrudRepository<ProductInventory,Integer> {

//     ProductInventory getProductInventory(Product product, Store store);

//     ProductInventory findByProductandStore(Product product, Store store);
     ProductInventory findByProductAndStore(Product product, Store store);
}
