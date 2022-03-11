package com.csci5308.w22.wiseshopping.exceptions;

/**
 * @author Elizabeth James
 */
public class MenuInterruptedException extends RuntimeException {
    private String message;
    public MenuInterruptedException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
