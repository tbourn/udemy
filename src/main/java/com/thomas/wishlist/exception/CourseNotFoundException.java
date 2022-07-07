package com.thomas.wishlist.exception;

public class CourseNotFoundException extends Exception {

    public CourseNotFoundException(int courseId) {
        super("Could not find course " + courseId);
    }

    public CourseNotFoundException(String message) {
        super(message);
    }

}
