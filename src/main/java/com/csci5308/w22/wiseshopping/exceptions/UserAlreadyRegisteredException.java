package com.csci5308.w22.wiseshopping.exceptions;

/**
 * @author Elizabeth James
 */
public class UserAlreadyRegisteredException extends RuntimeException {
    private String message;
    public UserAlreadyRegisteredException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
