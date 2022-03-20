package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.Tags;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nilesh
 */
@Repository
public interface TagsRepository extends CrudRepository<Tags,Integer> {

    //Tags
    @Query(value = "SELECT * FROM tags WHERE product_id = ?1", nativeQuery = true)
    List<Tags> findByProductId(int productId);
}
