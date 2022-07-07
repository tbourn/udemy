package com.thomas.wishlist.service;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.exception.CourseNotFoundException;

import java.util.List;

public interface CourseService {
    Course createCourse(Course course);

    Course findById(Integer courseId) throws CourseNotFoundException;

    Course updateCourse(Course course, Integer courseId) throws CourseNotFoundException;

    List<Course> findAllCourse();

    boolean deleteCourseById(Integer courseId) throws CourseNotFoundException;

    boolean deleteCourseByName(String name) throws CourseNotFoundException;
}
