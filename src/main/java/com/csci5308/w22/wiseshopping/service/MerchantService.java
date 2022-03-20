package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.exceptions.NoSuchUserException;
import com.csci5308.w22.wiseshopping.exceptions.UserAlreadyRegisteredException;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.repository.MerchantRepository;
import com.csci5308.w22.wiseshopping.repository.ProductCategoryRepository;
import com.csci5308.w22.wiseshopping.repository.ProductInventoryRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @author Elizabeth James
 */

@Service
public class MerchantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantService.class);

    @Autowired
    MerchantRepository merchantRepository;

    /**
     * inserts a merchant into table
     *
     * @param name     name of merchant
     * @param email    email of merchant
     * @param password password of merchant
     * @return true if success, else false
     */
    @Transactional
    public Merchant registerMerchant(String name, String email, String password) {
        if (name == null || name.equals(" ") || name.length()==0) {
            throw new IllegalArgumentException("name cannot be null or empty or blank");
        }
        if (password == null || password.equals(" ") || password.length()==0) {
            throw new IllegalArgumentException("password cannot be null or empty or blank");
        }

        if (email == null || email.length()==0 || email.equals(" ")) {
            throw new IllegalArgumentException("email cannot be null or empty or blank");
        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("given email is not valid");
        }

        Merchant merchant = merchantRepository.findMerchantByEmail(email);
        if (merchant != null) {
            throw new UserAlreadyRegisteredException(email + " is already registered");
        }
        merchant = new Merchant(name, email, password);
        merchantRepository.save(merchant);
        LOGGER.info("You have been registered successfully");
        return merchant;
    }

    /**
     * deletes a store from table
     *
     * @param email email of the merchant
     * @return true, if success; else false
     */
    @Transactional
    public boolean removeMerchant(String email) {
        if (email == null || email.length()==0 || email.equals(" ")) {
            throw new IllegalArgumentException("email cannot be null or empty or blank");
        }
        int id = merchantRepository.deleteByEmail(email);
        if (id > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    public Merchant loginMerchant(String email, String password) {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
        if (email.equals(" ") || email.length()==0) {
            throw new IllegalArgumentException("email cannot be empty");
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("given email id is not valid");
        }

        if (password == null) {
            throw new IllegalArgumentException("password cannot be null");
        }

        if(password.length()==0 || password.equals(" "))
        {

            throw new IllegalArgumentException("password cannot be empty");
        }

        String hashedPassword = DigestUtils.sha256Hex(password);
        Merchant merchant = merchantRepository.findMerchantByEmailAndPassword(email, hashedPassword);

        if (merchant != null) {
            LOGGER.info("You have logged in successfully");
            return merchant;
        } else {
            throw new NoSuchUserException("Invalid credentials. Merchant does not exist");
        }
    }

    public Merchant getMerchantByEmail(String email) {
        return merchantRepository.findMerchantByEmail(email);
    }


}
