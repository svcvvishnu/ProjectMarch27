package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.models.Merchant;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.repository.StoreRepository;
import com.csci5308.w22.wiseshopping.utils.Constants;
import com.csci5308.w22.wiseshopping.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.List;
import java.util.Map;

/**
 * this method acts like a service for store
 * @author Elizabeth James
 */
@Service
public class StoreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private LocationService locationService;


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
    public Store addStore(String name, String businessType, Time startTime, Time endTime, String contact, Merchant merchant, Location location){

        if (name == null || name.length() == 0 || name.equals(" ")){
            throw new IllegalArgumentException("storeName cannot be null or empty or blank");
        }
        if (businessType ==  null  || businessType.length()==0 || businessType.equals(" ")){
            throw new IllegalArgumentException("businessType cannot be null or empty or blank");
        }

        if (startTime == null){
            throw new IllegalArgumentException("startTime cannot be null or empty or blank");
        }

        if (endTime == null ){
            throw new IllegalArgumentException("endTime cannot be null or empty or blank");
        }

        if (contact ==  null || contact.equals(" ") || contact.length()==0 ){
            throw new IllegalArgumentException("contactNumber cannot be null or empty or blank");
        }

        if (merchant == null){
            throw new IllegalArgumentException("merchant cannot be null");
        }

        if (location == null){
            throw new IllegalArgumentException("location cannot be null");
        }

        Store store = new Store(name,startTime,endTime,businessType,contact,location,merchant);
        storeRepository.save(store);
        LOGGER.info("Store {} is added",store.getName());

        return store;
    }

    public Store updateStore(Store store, Map<String, String> attributes){
        // validate inputs
        if (store == null || attributes == null){
            throw new IllegalArgumentException("Store or attributes cannot be null");
        }
        boolean recorded = false;


        for (Map.Entry<String, String> entry : attributes.entrySet()) {

            if ("none".equals(entry.getValue())) {

                switch (entry.getKey()) {
                    case Constants.KEY_NAME:
                        store.setName(attributes.get(Constants.KEY_NAME));
                        break;
                    case Constants.KEY_START_TIME:
//                        Time startTime = Util.parseTime(attributes.get(Constants.KEY_START_TIME));
                        store.setStartTime(attributes.get(Constants.KEY_START_TIME));
                        break;
                    case Constants.KEY_END_TIME:
//                        Time endTime = Util.parseTime(attributes.get(Constants.KEY_END_TIME));
                        store.setEndTime(attributes.get(Constants.KEY_END_TIME));
                        break;
                    case Constants.KEY_TYPE_OF_BUSINESS:
                        store.setType(attributes.get(Constants.KEY_TYPE_OF_BUSINESS));
                        break;
                    case Constants.KEY_CONTACT:
                        store.setContact(attributes.get(Constants.KEY_CONTACT));
                        break;
                    case Constants.KEY_LOCATION:
                        //TODO
                        Location location = locationService.getLocationByID(attributes.get(Constants.KEY_LOCATION));
                        store.setLocation(location);
                        break;
                }
            }
        }
        Store updatedStore = storeRepository.save(store);
        LOGGER.info("Store {} is updated",store.getName());
        return updatedStore;
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
        return storeRepository.findByMerchantID(merchant.getId());
    }

    @Transactional
    public boolean remove(int id){
        int deletedId = storeRepository.deleteById(id);
        if (deletedId > 0){
            LOGGER.info("Store id {} is deleted",id);
            return true;
        }
        return false;
    }



    public Store getStoreById(int id) {
        return storeRepository.findById(id);
    }
}
