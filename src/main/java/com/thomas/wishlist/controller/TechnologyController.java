package com.thomas.wishlist.controller;

import com.thomas.wishlist.dto.ListOfTechnologiesRequest;
import com.thomas.wishlist.dto.ListOfTechnologiesResponse;
import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.TechnologyNotFoundException;
import com.thomas.wishlist.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TechnologyController {

    private final TechnologyService technologyService;

    // endpoint: Create a new Technology record
    @PostMapping("/technologies")
    public ResponseEntity<?> createTechnology(@Valid @RequestBody Technology technology) {
        var technologyResponse = technologyService.createTechnology(technology);
        return new ResponseEntity<>(technologyResponse, HttpStatus.CREATED);
    }

    // endpoint: Update an existing Technology record
    @PutMapping("/technologies/{id}")
    public ResponseEntity<?> updateTechnology(@Valid @RequestBody Technology technology, @PathVariable Integer id)
            throws TechnologyNotFoundException {
        if (null != technology) {
            return new ResponseEntity<>(this.technologyService.updateTechnology(technology, id), HttpStatus.OK);
        }
        return new ResponseEntity<>("TECHNOLOGY IS NOT FOUND", HttpStatus.NOT_FOUND);
    }

    // endpoint: Retrieve the list of all the Technologies records
    @GetMapping("/technologies")
    public ResponseEntity<?> findAll() {
        var technologyList = this.technologyService.findAllTechnology();
        technologyList.sort(Comparator.comparingInt(Technology::getTechnologyId));
        return new ResponseEntity<>(technologyList, HttpStatus.OK);
    }

    // endpoint: Retrieve a map with: keys all the Technology Names and values the
    //list with the corresponding Courses, ordered by their Name.
    @GetMapping("/technologiesAndCourses")
    public ResponseEntity<?> findTechnologiesAndCourses() {
        return new ResponseEntity<>(this.technologyService.convertList(
                this.technologyService.findAllTechnology()), HttpStatus.OK);
    }

    // endpoint: Retrieve a Technology based on its Name
    @GetMapping("/technologies/name")
    public ResponseEntity<?> getTechnologyByName(@RequestParam String name) throws TechnologyNotFoundException {
        if (null != name && technologyService.findTechnologyByName(name).isPresent()) {
            return new ResponseEntity<>(technologyService.findTechnologyByName(name), HttpStatus.OK);
        }
        return new ResponseEntity<>("TECHNOLOGY " + name + " IS NOT FOUND", HttpStatus.NOT_FOUND);
    }

    // endpoint: Delete a Technology based on its Name
    @DeleteMapping("/technologies/name")
    public ResponseEntity<?> deleteTechnologyByName(@RequestParam String name) throws TechnologyNotFoundException {
        if (null != name && technologyService.findTechnologyByName(name).isPresent()) {
            if (this.technologyService.deleteTechnologyByName(name)) {
                return new ResponseEntity<>("TECHNOLOGY " + name + " IS DELETED", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("TECHNOLOGY IS NOT FOUND", HttpStatus.NOT_FOUND);
    }

    // endpoint: Create a new list of Technologies
    @PostMapping("/technologies-list")
    public ResponseEntity<?> createTechnologyList(@Valid @RequestBody ListOfTechnologiesRequest technologies) {
        if (null!=technologies){
            List<Technology> technologyList = new ArrayList<>();
            for (Technology tech:technologies.getTechnologies()) {
                technologyList.add(tech);
                // to fix persistence
//                technologyService.createTechnology(tech);
            }
            ListOfTechnologiesResponse listofTechnologiesResponse = new ListOfTechnologiesResponse();
            listofTechnologiesResponse.setTechnologies(technologyList);
            return new ResponseEntity<>(listofTechnologiesResponse, HttpStatus.CREATED);
        }

        return new ResponseEntity<>("TECHNOLOGY LIST IS NOT FOUND", HttpStatus.NOT_FOUND);
    }

}
