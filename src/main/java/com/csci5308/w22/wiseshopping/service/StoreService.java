package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.repository.StoreRepository;
import com.csci5308.w22.wiseshopping.screens.LoginScreen;
import com.csci5308.w22.wiseshopping.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;

/**
 * this method acts like a service for store
 * @author Elizabeth James
 */
@Service
public class StoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);

    @Autowired
    private StoreRepository storeRepository;


    /**
     * add a store to the table
     * @param name name of store
     * @param businessType type of business of the store
     * @param startTime starting time of business
     * @param endTime ending time of business
     * @param contact contact info
     * @param merchant merchant that the store belongs to
     * @param location location of the store
     * @return true, if success; else false
     */
    @Transactional
    public Store addStore(String name, String businessType, String startTime, String endTime, String contact, Merchant merchant, Location location){

        if (name ==  null || name.isEmpty() || name.isBlank() ){
            throw new IllegalArgumentException("storeName cannot be null or empty or blank");
        }
        if (businessType ==  null || businessType.isEmpty() || businessType.isBlank() ){
            throw new IllegalArgumentException("businessType cannot be null or empty or blank");
        }

        if (startTime == null || startTime.isBlank() || startTime.isEmpty()){
            throw new IllegalArgumentException("startTime cannot be null or empty or blank");
        }

        if (endTime == null || endTime.isBlank() || endTime.isEmpty()){
            throw new IllegalArgumentException("endTime cannot be null or empty or blank");
        }

        if (contact ==  null || contact.isEmpty() || contact.isBlank() ){
            throw new IllegalArgumentException("contactNumber cannot be null or empty or blank");
        }

        if (merchant == null){
            throw new IllegalArgumentException("merchant cannot be null");
        }

        if (location == null){
            throw new IllegalArgumentException("location cannot be null");
        }

        Time startingTime = Util.parseTime(startTime);
        Time endingTime = Util.parseTime(endTime);
        Store store = new Store(name,startingTime,endingTime,businessType,contact,location,merchant);
        storeRepository.save(store);
        LOGGER.info("Store {} is added",store.getStoreName());

        return store;
    }

    /**
     * deletes a store from table
     * @param store store
     * @return true, if success; else false
     */
    @Transactional
    public boolean remove(Store store) {
        if (store == null){
            throw new IllegalArgumentException("store cannot be null");
        }
        storeRepository.delete(store);
        return true;
    }

    @Transactional
    public List<Store> getAllStoresBelongingToAMerchant(Merchant merchant){
        return storeRepository.findByMerchantID(merchant.getMerchantId());
    }

    @Transactional
    public boolean remove(int id){
        int deletedId = storeRepository.deleteByStoreId(id);
        if (deletedId > 0){
            LOGGER.info("Store id {} is deleted",id);
            return true;
        }
        return false;
    }
}
