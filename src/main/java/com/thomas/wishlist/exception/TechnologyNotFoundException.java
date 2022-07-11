package com.thomas.wishlist.exception;

public class TechnologyNotFoundException extends Exception {

    public TechnologyNotFoundException(String message) {
//        super(message);
        System.out.println("TechnologyNotFoundException: THE TECHNOLOGY WAS NOT FOUND");
    }
}
