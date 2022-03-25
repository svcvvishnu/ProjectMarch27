package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.utils.MailNotifier;
import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private MailNotifier mailNotifier;

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

    public boolean alertSubscribers(Product product, Store store, float price) {
        boolean success = false;
        if(product == null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        if(price < 0){
            throw new IllegalArgumentException("price amount cannot be less than 0");
        }

        List<Subscription> subscriptionList = subscriptionRepository.findByProductId(product.getProductId());

        List<User> usersToBeAlerted = new ArrayList<>();
        for (Subscription subscription : subscriptionList){
            if (subscription.getPriceAlert() >= price){
                usersToBeAlerted.add(subscription.getUser());
            }
        }

        success = mailNotifier.sendPriceAlerts(usersToBeAlerted, product.getProductName(), store.getName(), price);
        return success;
    }

    public List<Subscription> getAllSubscriptionsOfUser(User user) {
        return subscriptionRepository.findByUser(user);
    }

    public boolean removeSubscription(int id) {
        subscriptionRepository.deleteById(id);
        return true;
    }

    public boolean updateSubscription(int subId, float priceAlert) {
        Subscription subscription = subscriptionRepository.findById(subId).orElse(null);
        if (subscription!=null){
            subscription.setPriceAlert(priceAlert);
            subscriptionRepository.save(subscription);
            return true;
        }
        else {
            LOGGER.error("No such subscription id");
            return false;
        }

    }
}

