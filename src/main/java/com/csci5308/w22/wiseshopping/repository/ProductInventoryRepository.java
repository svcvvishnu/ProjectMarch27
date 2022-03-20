package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.models.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nilesh
 */
@Repository
public interface ProductInventoryRepository extends CrudRepository<ProductInventory,Integer> {


     //ProductInventory getProductInventory(Product product, Store store);
     //ProductInventory findByProductandStore(Product product, Store store);
     ProductInventory findByProductAndStore(Product product, Store store);
     @Query(value ="SELECT * FROM product_inventory as inv JOIN store as stores JOIN location as locations on inv.store_id=stores.store_id AND stores.location_id=locations.location_id  where inv.product_id = ?1 AND locations.location_name= ?2", nativeQuery = true)
     List<ProductInventory> findByProductId(int product, String location);
}
