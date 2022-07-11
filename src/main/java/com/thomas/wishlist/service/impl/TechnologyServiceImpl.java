package com.thomas.wishlist.service.impl;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.TechnologyNotFoundException;
import com.thomas.wishlist.repository.TechnologyRepository;
import com.thomas.wishlist.service.TechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TechnologyServiceImpl implements TechnologyService {
    private final TechnologyRepository technologyRepository;

    /**
     * Creates a new Technology object.
     *
     * @param technology of Technology type
     * @return Technology object
     */
    @Override
    public Technology createTechnology(Technology technology) {
        return this.technologyRepository.save(technology);
    }

    /**
     * Returns a Treemap key-and-value-sorted by case-insensitive order. Creates a deep copy of the list param of the
     * same size. The copy list is filled with new Technology items with the copy constructor. Sorts the list by name.
     *
     * @param list of List<Technology>
     * @return Map<String, List < Course>>
     */
    @Override
    public Map<String, List<Course>> convertList(List<Technology> list) {
        Map<String, List<Course>> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        List<Technology> copyArray = new ArrayList<>(list.size());
        list.forEach(technology -> copyArray.add(new Technology(technology)));
        copyArray.forEach(technology -> {
            map.put(technology.getName(), technology.getCourses());
            technology.getCourses().sort(Comparator.comparing(Course::getName));
        });
        return map;
    }

    /**
     * Returns an Optional<Technology>
     *
     * @param name of String type
     * @return Optional<Technology>
     */
    @Override
    public Optional<Technology> findTechnologyByName(String name) {
        return technologyRepository.findByName(name);
    }

    /**
     * Updates a technology given an ID number.
     *
     * @param technology   of Technology type
     * @param technologyId of Integer type
     * @return Technology object
     * @throws TechnologyNotFoundException
     */
    @Override
    public Technology updateTechnology(Technology technology, Integer technologyId) throws TechnologyNotFoundException {
        if (technologyRepository.findById(technologyId).isPresent()) {
            return this.technologyRepository.findById(technologyId).map(tech -> {
                tech.setName(technology.getName());
                return technologyRepository.save(tech);
            }).orElseThrow(
                    () -> new TechnologyNotFoundException("COULD NOT FIND TECHNOLOGY " + technology.getTechnologyId()));
        }
        return null;
    }

    /**
     * Returns a List if all the technologies.
     *
     * @return List<Technology>
     */
    @Override
    public List<Technology> findAllTechnology() {
        return this.technologyRepository.findAll();
    }

    /**
     * Deletes a technology by name.
     *
     * @param technologyName of String type
     * @return boolean
     * @throws TechnologyNotFoundException
     */
    @Override
    public boolean deleteTechnologyByName(String technologyName) throws TechnologyNotFoundException {
        return this.technologyRepository.findByName(technologyName).map(tech -> {
            this.technologyRepository.delete(tech);
            return true;
        }).orElseThrow(() -> new TechnologyNotFoundException("NO RECORD FOUND FOR TECHNOLOGY " + technologyName));
    }

}
