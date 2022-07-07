package com.thomas.wishlist.controller;

import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.TechnologyNotFoundException;
import com.thomas.wishlist.repository.TechnologyRepository;
import com.thomas.wishlist.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TechnologyController {

    private final TechnologyService technologyService;

    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyController(TechnologyService technologyService, TechnologyRepository technologyRepository) {
        this.technologyService = technologyService;
        this.technologyRepository = technologyRepository;
    }

    // endpoint: Create a new Technology record
    @PostMapping("/technologies")
    public ResponseEntity<?> createTechnology(@Valid @RequestBody Technology technology) {
        return new ResponseEntity<>(this.technologyService.createTechnology(technology), HttpStatus.CREATED);
    }

    // endpoint: Retrieve the list of all the Technologies records
    @GetMapping("/technologies")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(this.technologyService.findAllTechnology(), HttpStatus.OK);
    }

    // endpoint: Retrieve a map with: keys all the Technology Names and values the
    //list with the corresponding Courses, ordered by their Name.
    @GetMapping("/technologiesAndCourses")
    public ResponseEntity<?> findTechnologiesAndCourses() {
        return new ResponseEntity<>(this.technologyService.convertList(
                this.technologyService.findAllTechnology()), HttpStatus.OK);
    }


    // endpoint: Retrieve a Technology based on its ID
//    @GetMapping("/technologies/{id}")
//    public ResponseEntity<?> findById(@PathVariable Integer id) throws TechnologyNotFoundException {
//        return new ResponseEntity<>(this.technologyService.findById(id), HttpStatus.OK);
//    }

    // endpoint: Retrieve a Technology based on its Name
    @GetMapping("/technologies/name")
    public ResponseEntity<Optional<?>> getTechnologyByName(@RequestParam String name) {
        return new ResponseEntity<>(technologyRepository.findByName(name), HttpStatus.OK);
    }

    // endpoint: Update an existing Technology record
    @PutMapping("/technologies/{id}")
    public ResponseEntity<?> updateTechnology(@Valid @RequestBody Technology technology, @PathVariable Integer id)
            throws TechnologyNotFoundException {
        return new ResponseEntity<>(this.technologyService.updateTechnology(technology, id), HttpStatus.OK);
    }

    // endpoint: Delete a Technology based on its ID
//    @DeleteMapping("/technologies/{id}")
//    public ResponseEntity<?> deleteTechnology(@PathVariable Integer id) throws TechnologyNotFoundException {
//        if (this.technologyService.deleteTechnologyById(id)) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    // endpoint: Delete a Technology based on its Name
    @DeleteMapping("/technologies/name")
    public ResponseEntity<?> deleteTechnologyByName(@RequestParam String name) throws TechnologyNotFoundException {
        if (this.technologyService.deleteTechnologyByName(name)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
