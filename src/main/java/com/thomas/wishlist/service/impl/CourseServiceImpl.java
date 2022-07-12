package com.thomas.wishlist.service.impl;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.exception.CourseNotFoundException;
import com.thomas.wishlist.repository.CourseRepository;
import com.thomas.wishlist.repository.TechnologyRepository;
import com.thomas.wishlist.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    private final TechnologyRepository technologyRepository;

    /**
     * Creates a new Course object
     *
     * @param course of Course type
     * @return Course object
     */
    @Override
    public Course createCourse(Course course) {
        if (course.getTechnologyId() != null) {
            var technology = this.technologyRepository
                    .findById(course.getTechnologyId().getTechnologyId());
            technology.ifPresent(course::setTechnologyId);
        }
        return this.courseRepository.save(course);
    }

    /**
     * Finds a course given a number.
     *
     * @param courseId of Integer type
     * @return Course object
     * @throws CourseNotFoundException
     */
    @Override
    public Course findById(Integer courseId) throws CourseNotFoundException {
        return this.courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }

    /**
     * Returns an Optional<Course>
     *
     * @param name of String type
     * @return Optional<Course>
     */
    @Override
    public Optional<Course> findCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    /**
     * Updates a course given an ID number.
     *
     * @param course   of Course type
     * @param courseId of Integer type
     * @return Course object
     * @throws CourseNotFoundException
     */
    @Override
    public Course updateCourse(Course course, Integer courseId) throws CourseNotFoundException {
        if (courseRepository.findById(courseId).isPresent()) {
            return this.courseRepository.findById(courseId).map(tempCourse -> {
                tempCourse.setName(course.getName());
                tempCourse.setCompletionPercentage(course.getCompletionPercentage());
                tempCourse.setTechnologyId(course.getTechnologyId());
                return this.courseRepository.save(tempCourse);
            }).orElseThrow(() -> new CourseNotFoundException(course.getCourseId()));
        }
        return null;
    }

    /**
     * Returns a List of all the courses
     *
     * @return List<Course></Course>
     */
    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }

    /**
     * Deletes a course by name.
     *
     * @param courseName of String type
     * @return boolean
     * @throws CourseNotFoundException
     */
    @Override
    public boolean deleteCourseByName(String courseName) throws CourseNotFoundException {
        return this.courseRepository.findByName(courseName).map(tempCourse -> {
            this.courseRepository.delete(tempCourse);
            return true;
        }).orElseThrow(() -> new CourseNotFoundException("NO RECORD FOUND FOR COURSE " + courseName));
    }
}
