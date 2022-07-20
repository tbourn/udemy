package com.thomas.wishlist.controller;

import com.thomas.wishlist.dto.CourseRequest;
import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.CourseNotFoundException;
import com.thomas.wishlist.repository.CourseRepository;
import com.thomas.wishlist.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CourseController {
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    // endpoint: Create a new Course under a specific Technology
    @PostMapping("/courses")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseRequest courseRequest) {

        var course = new Course();
        course.setName(courseRequest.getName());
        course.setCompletionPercentage(courseRequest.getCompletionPercentage());

        var technology = new Technology();
        technology.setTechnologyId(courseRequest.getTechnologyId());
        course.setTechnologyId(technology);

        return new ResponseEntity<>(this.courseService.createCourse(course), HttpStatus.CREATED);
    }

    // endpoint: Update an existing Course
    @PutMapping("courses/{id}")
    public ResponseEntity<?> updateCourse(@Valid @RequestBody CourseRequest courseRequest, @PathVariable Integer id)
            throws CourseNotFoundException {
        if (null != courseRequest) {
            System.err.println("TO BE FIXED");
            // Check if id exists

            var course = courseService.findById(id);
            if (course.getName().equals(courseRequest.getName())){
                course.setName(courseRequest.getName());
                course.setCompletionPercentage(courseRequest.getCompletionPercentage());

                return new ResponseEntity<>(this.courseService.updateCourse(course, id), HttpStatus.OK);
            }

        }
        return new ResponseEntity<>("COURSE IS NOT FOUND", HttpStatus.NOT_FOUND);
    }

    // endpoint: Retrieve the list of all the Courses records
    @GetMapping("/courses")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.courseService.findAllCourse(), HttpStatus.OK);
    }

    // endpoint: Retrieve a Course based on its Name
    @GetMapping("/courses/name")
    public ResponseEntity<?> getCourseByName(@RequestParam String name) {
        if (null != name && courseService.findCourseByName(name).isPresent()) {
            return new ResponseEntity<>(courseService.findCourseByName(name), HttpStatus.OK);
        }
        return new ResponseEntity<>("TECHNOLOGY " + name + " IS NOT FOUND", HttpStatus.NOT_FOUND);
    }

    // endpoint: Delete a Course based on its Name
    @DeleteMapping("/courses/name")
    public ResponseEntity<?> deleteCourseByName(@RequestParam String name) throws CourseNotFoundException {
        if (null != name && courseService.findCourseByName(name).isPresent()) {
            if (this.courseService.deleteCourseByName(name)) {
                return new ResponseEntity<>("COURSE " + name + " IS DELETED", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("COURSE " + name + " IS NOT FOUND", HttpStatus.NOT_FOUND);
    }
}
