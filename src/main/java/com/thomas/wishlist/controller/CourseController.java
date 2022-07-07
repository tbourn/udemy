package com.thomas.wishlist.controller;

import com.thomas.wishlist.dto.CourseRequest;
import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.CourseNotFoundException;
import com.thomas.wishlist.repository.CourseRepository;
import com.thomas.wishlist.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;
//    private final TechnologyService technologyService;
    private final CourseRepository courseRepository;

//    public CourseController(CourseService courseService, CourseRepository courseRepository,
//                            TechnologyService technologyService) {
//        this.courseService = courseService;
//        this.courseRepository = courseRepository;
//        this.technologyService = technologyService;
//    }

    public CourseController(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    // endpoint: create a new Course under a specific Technology
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setCompletionPercentage(courseRequest.getCompletionPercentage());
        Technology technology = new Technology();
        technology.setTechnologyId(courseRequest.getTechnologyId());
        course.setTechnologyId(technology);
        return new ResponseEntity<>(this.courseService.createCourse(course), HttpStatus.CREATED);
    }

    // endpoint: Retrieve the list of all the Courses records
    @GetMapping("/courses")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.courseService.findAllCourse(), HttpStatus.OK);
    }

    // endpoint: Retrieve a Course based on its ID
//    @GetMapping("/courses/{id}")
//    public ResponseEntity<?> findById(@PathVariable Integer id) throws CourseNotFoundException {
//        return new ResponseEntity<>(this.courseService.findById(id), HttpStatus.OK);
//    }

    // endpoint: Retrieve a Course based on its Name
    @GetMapping("/courses/name")
    public ResponseEntity<?> getCourseByName(@RequestParam String name) {
        return new ResponseEntity<>(courseRepository.findByName(name), HttpStatus.OK);
    }

    // endpoint: update an existing Course
    @PutMapping("courses/{id}")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseRequest courseRequest, @PathVariable Integer id)
            throws CourseNotFoundException {
//        Course course = new Course();
        Course course = courseService.findById(id);
        course.setName(courseRequest.getName());
        course.setCompletionPercentage(courseRequest.getCompletionPercentage());

        return new ResponseEntity<>(this.courseService.updateCourse(course, id), HttpStatus.OK);
    }

    // endpoint: Delete a Course based on its ID
//    @DeleteMapping("/courses/{id}")
//    public ResponseEntity<?> deleteCourse(@PathVariable Integer id) throws CourseNotFoundException {
//        if (this.courseService.deleteCourseById(id)) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    // endpoint: Delete a Course based on its Name
    @DeleteMapping("/courses/name")
    public ResponseEntity<?> deleteCourseByName(@RequestParam String name) throws CourseNotFoundException {
        if (this.courseService.deleteCourseByName(name)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
