package com.csci5308.w22.wiseshopping.repository;

import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Elizabeth James
 */
@Repository
public interface MerchantRepository extends CrudRepository<Merchant,Integer> {
     Integer deleteByEmail(String email);
     Merchant findMerchantByEmail(String email);
     Merchant findMerchantByEmailAndPassword(String email, String password);
}
