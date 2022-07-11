package com.thomas.wishlist.service;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.exception.CourseNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course createCourse(Course course);

    Course findById(Integer courseId) throws CourseNotFoundException;

    Optional<Course> findCourseByName(String name);

    Course updateCourse(Course course, Integer courseId) throws CourseNotFoundException;

    List<Course> findAllCourse();

    boolean deleteCourseByName(String name) throws CourseNotFoundException;
}
