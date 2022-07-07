package com.thomas.wishlist.repository;

import com.thomas.wishlist.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name);
}
