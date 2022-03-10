package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.models.Subscription;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.SubscriptionRepository;
import com.csci5308.w22.wiseshopping.repository.ProductInventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



/**
 * @author Elizabeth James
 */
@Service
public class SubscriptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantService.class);

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    public Subscription addSubscription(User user, Product product, float priceAlert){
        //input validation
        if(user == null){
            throw new IllegalArgumentException("User cannot be null");
        }
        if(product == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(priceAlert < 0){
            throw new IllegalArgumentException("price alert amount cannot be less than 0");
        }

        Subscription subscription = subscriptionRepository.findByUserAndProduct(user, product);

        if (subscription == null){
            subscription.setProduct(product);
            subscription.setUser(user);
        }
        subscription.setPriceAlert(priceAlert);
        subscriptionRepository.save(subscription);
        LOGGER.info("Subscription with userID: {}, productId: {}, priceAlert: {}  saved");
        return subscription;
    }

    public void alertSubscribers(Product product, float price) {
        if(product == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(price < 0){
            throw new IllegalArgumentException("price amount cannot be less than 0");
        }

        List<Subscription> subscriptionList = subscriptionRepository.findByProductId(product.getProductId());

        List<User> userIdsToBeAlerted = new ArrayList<>();
        for (Subscription subscription : subscriptionList){
            if (subscription.getPriceAlert() >= price){
                userIdsToBeAlerted.add(subscription.getUser());
            }
        }

       // TODO send email
    }

}

