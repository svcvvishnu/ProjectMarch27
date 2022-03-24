package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Location;
import com.csci5308.w22.wiseshopping.repository.LocationRepository;
import com.csci5308.w22.wiseshopping.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * this method acts like a service for location
 * @author Elizabeth James
 */
@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

    /**
     * inserts location to table
     * @param name name of location
     * @param zipcode zip code of location
     * @param province province
     * @param country country
     * @return true, if successful; else false
     */
    @Transactional
    public Location addLocation(String name, String zipcode, String province, String country){

        if (!Util.isValidString(name)){
            throw new IllegalArgumentException("locationName cannot be null or empty or blank");
        }

        if (!Util.isValidString(zipcode)){
            throw new IllegalArgumentException("zipcode cannot be null or empty or blank");
        }
        if (!Util.isValidString(province)){
            throw new IllegalArgumentException("province cannot be null or empty or blank");
        }
        if (!Util.isValidString(country)){
            throw new IllegalArgumentException("country cannot be null or empty or blank");
        }

        // TODO : check for uniqueness
        Location location = new Location(name,zipcode, province, country);
        locationRepository.save(location);
        return location;
    }

    /**
     * deletes location from table
     * @param location location
     * @return true, if deleted; else false
     */
    @Transactional
    public boolean remove(Location location) {
        if (location == null){
            throw new IllegalArgumentException("location cannot be null");
        }
        locationRepository.delete(location);
        return true;
    }

    public Location getLocationByID(String s) {
       return locationRepository.findById(Integer.parseInt(s)).orElse(null);
    }
}
