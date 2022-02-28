package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.exceptions.UserAlreadyRegisteredException;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.MerchantRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @author Elizabeth James
 */

@Service
public class MerchantService {
    @Autowired
    MerchantRepository merchantRepository;

    /**
     * inserts a merchant into table
     * @param name name of merchant
     * @param email email of merchant
     * @param password password of merchant
     * @return true if success, else false
     */
    @Transactional
    public Merchant registerMerchant(String name, String email, String password) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or empty or blank");
        }
        if (password == null || password.isEmpty() || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty or blank");
        }

        if (email == null || email.isBlank() || email.isEmpty()) {
            throw new IllegalArgumentException("email cannot be null or empty or blank");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("given email is not valid");
        }

        Merchant merchant = merchantRepository.findMerchantByEmail(email);
        if (merchant!=null){
            throw new UserAlreadyRegisteredException(email + " is already registered");
        }
        merchant = new Merchant(name, email, password);
        merchantRepository.save(merchant);
        return merchant;
    }

    /**
     * deletes a store from table
     * @param email email of the merchant
     * @return true, if success; else false
     */
    @Transactional
    public boolean removeMerchant(String email) {
        if (email == null || email.isBlank() || email.isEmpty()) {
            throw new IllegalArgumentException("email cannot be null or empty or blank");
        }
        int id = merchantRepository.deleteByEmail(email);
        if (id > 0){
            return true;
        }
        return false;
    }

    @Transactional
    public Merchant loginMerchant(String email, String password) {
        if (email == null) {
            throw new NullPointerException("email cannot be null");
        }
        if (email.isEmpty() || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be empty");
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("given email id is not valid");
        }

        if(password==null)
        {
            throw new NullPointerException("password cannot be null");
        }
        if(password.isBlank() || password.isEmpty())
        {
            throw new IllegalArgumentException("password cannot be empty");
        }

        Merchant merchant = merchantRepository.findMerchantByEmail(email);
        return merchant;



    }


    public Merchant getMerchantByEmail(String email){
        return merchantRepository.findMerchantByEmail(email);
    }

}
