package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Elizabeth James
 */
@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
}
