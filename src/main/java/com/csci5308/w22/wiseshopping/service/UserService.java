package com.csci5308.w22.wiseshopping.service;
import com.csci5308.w22.wiseshopping.exceptions.UserAlreadyRegisteredException;
import com.csci5308.w22.wiseshopping.models.User;
import com.csci5308.w22.wiseshopping.repository.UserRepository;
import com.csci5308.w22.wiseshopping.utils.Constants;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
/**
 * @author Pavithra Gunasekaran
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantService.class);
    @Autowired
    UserRepository userRepository;

    @Transactional
    public User registerUser(String firstName, String lastName, String email, String password, String contact) {
        if (firstName == null || firstName.equals(" ") || firstName.length()==0) {
            throw new IllegalArgumentException("firstName cannot be null or empty or blank");
        }
        if (lastName == null || lastName.equals(" ") || lastName.length()==0) {
            throw new IllegalArgumentException("lastName cannot be null or empty or blank");
        }
        if (contact == null || contact.equals(" ") || contact.length()==0) {
            throw new IllegalArgumentException("contact number cannot be null or empty or blank");
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
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new UserAlreadyRegisteredException(email + " is already registered");
        }
        user = new User(firstName, lastName, email, password, contact);
        userRepository.save(user);
        LOGGER.info("User has been successfully registered");
        return user;
    }
    public User updateUserDetails(String email, Map<String, String> userDetails) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Could not find any user with email id:" + email);
        }
        for (Map.Entry<String, String> entry : userDetails.entrySet()) {
            if (entry.getValue() == null || entry.getValue().equals(" ") || entry.getValue().length()==0) {
                throw new IllegalArgumentException(entry.getKey() + " cannot be null or empty or blank");
            }
            switch (entry.getKey()) {
                case Constants.FIRST_NAME:
                    user.setFirstName(entry.getValue());
                    break;
                case Constants.LAST_NAME:
                    user.setLastName(entry.getValue());
                    break;
                case Constants.CONTACT:
                    user.setContact(entry.getValue());
                    break;
                default:
//log the invalid input value
            }
        }
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User loginUser(String email, String password) {
        if (email == null) {
            throw new IllegalArgumentException("email cannot be null");
        }
        if (email.equals(" ") || email.length() == 0) {
            throw new IllegalArgumentException("email cannot be empty");
        }

        if (email == null || email.length() == 0 || email.equals(" ")) {
            throw new IllegalArgumentException("email cannot be null or empty or blank");

        }
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Given email is not valid");
        }

        if (password == null) {
            throw new IllegalArgumentException("password cannot be null");
        }
        if (password.length() == 0 || password.equals(" ")) {
            throw new IllegalArgumentException("password cannot be empty");
        }
        User user = userRepository.findByEmailAndPassword(email, password);
        return user;
    }

    /**
     * deletes a user from table
     *
     * @param email email of the merchant
     * @return true, if success; else false
     */
    @Transactional
    public boolean removeUser(String email) {
        if (email == null || email.length()==0 || email.equals(" ")) {
            throw new IllegalArgumentException("email cannot be null or empty or blank");
        }
        int id = userRepository.deleteByEmail(email);
        if (id > 0) {
            return true;
        }
        return false;
    }
}
