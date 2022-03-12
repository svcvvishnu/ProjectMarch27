package com.csci5308.w22.wiseshopping.utils;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Elizabeth James
 */
@Component
public class MailNotifier {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendPriceAlerts(List<User> userList, String productName, String storeName, float price) {
        boolean success = false;
        SimpleMailMessage msg = new SimpleMailMessage();
        for (User user : userList) {
            msg.setTo(user.getEmail());
            msg.setSubject("Price drop in " + productName + "!");
            msg.setText("Prices for " + productName + " has dropped to " + price + ". Check your nearest store - " + storeName);

            javaMailSender.send(msg);
        }
        success = true;
        return success;

    }

}