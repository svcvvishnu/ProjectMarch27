package com.csci5308.w22.wiseshopping.screens;

import com.csci5308.w22.wiseshopping.exceptions.MenuInterruptedException;
import com.csci5308.w22.wiseshopping.factory.ScreenFactory;
import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.service.ProductService;
import com.csci5308.w22.wiseshopping.service.SubscriptionService;
import com.csci5308.w22.wiseshopping.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Elizabeth James
 */
@Component
public class SubscriptionScreen implements Screen{
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsScreen.class);

    private Scanner scanner;


    private ArrayList<String> validScreens;

    private SubscriptionService subscriptionService;

    private ProductService productService;

    private Merchant merchant;

    private User user;


    @Autowired
    public SubscriptionScreen(Scanner scanner, SubscriptionService subscriptionService) {
        this.scanner = scanner;
        this.subscriptionService = subscriptionService;
        validScreens= new ArrayList<>(Arrays.asList(Constants.LOGOUT));
    }

    @Override
    public boolean render(ScreenFactory screenFactory) {
        LOGGER.info("****SUBSCRIPTION SCREEN****");
        LOGGER.info("");
        LOGGER.info("Choose one of the following");
        LOGGER.info("Use v_, u_, d_ to add, update or delete followed subscription");
        LOGGER.info("eg: v_subscriptions, a_subscription, d_subscription, etc.");

        boolean success = false;
        try {
            String input = "";
            do{
            input = scan(scanner);
            if (input.equals("v_subscriptions")) {
                List<Subscription> subscriptionList = subscriptionService.getAllSubscriptionsOfUser(user);
                subscriptionList.stream().forEach( s -> LOGGER.info("Product name: {}, price alert: {}", s.getProduct().getProductName(), s.getPriceAlert()));
                success = true;
            } else if (input.equals("d_subscriptions")) {
                List<Subscription> subscriptionList = subscriptionService.getAllSubscriptionsOfUser(user);
                subscriptionList.stream().forEach( s -> LOGGER.info("SubscriptionId: {}, Product name: {}, price alert: {}", s.getId(), s.getProduct().getProductName(), s.getPriceAlert()));
                LOGGER.info("Enter the <id> to be deleted");
                int id = Integer.parseInt(scan(scanner));
                success = subscriptionService.removeSubscription(id);
            } else if (input.equals("a_subscriptions")) {
                // TODO : what can be done to remove user recall
                LOGGER.info("Enter <product_id> <price_alert>");
                int productId = Integer.parseInt(scan(scanner));
                float priceAlert = (float) Integer.parseInt(scan(scanner));
                Product product = productService.getProductById(productId);
                Subscription subscription = subscriptionService.addSubscription(user, product, priceAlert);
                success = subscription!=null;
            }
            else if (input.equals("u_subscriptions")){
                List<Subscription> subscriptionList = subscriptionService.getAllSubscriptionsOfUser(user);
                subscriptionList.stream().forEach( s -> LOGGER.info("SubscriptionId: {}, Product name: {}, price alert: {}", s.getId(), s.getProduct().getProductName(), s.getPriceAlert()));
                LOGGER.info("Enter the <id> <new_price_alert> to be updated");
                int subId = Integer.parseInt(scan(scanner));
                float priceAlert = (float) Integer.parseInt(scan(scanner));
                success = subscriptionService.updateSubscription(subId, priceAlert);
            }
            else if (input.equals("exit")){
                LOGGER.info("Exiting subscription menu...");
            }
            else {
                LOGGER.warn("Invalid input received...");
            }
        } while (!input.equals("exit"));


        } catch (MenuInterruptedException e) {
            getNavigations(screenFactory, validScreens, LOGGER, scanner);
        }
        return success;
    }

    @Override
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public void setUser(User user) {
        this.user = user;

    }
}

