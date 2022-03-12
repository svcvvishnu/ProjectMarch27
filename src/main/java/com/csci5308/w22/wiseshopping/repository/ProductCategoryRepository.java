package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.ProductCategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nilesh
 */
@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory,Integer> {

//    ProductCategory getProductCategoryById(int productCategoryId);
    ProductCategory findByProductCategoryId(int productCategoryId);


}
