package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Pavithra Gunasekaran
 */
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

//    User getUserByEmail(String email);
    User findByEmail(String email);
//    User getUserById(Integer userId);
    //Update this by Find by id //Check the method where it is used and it should start with Find by ID
    //Id column matches with the column_name of the database.

   User findByEmailAndPassword(String email, String password);

}
