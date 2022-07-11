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

    @Override
    public Technology createTechnology(Technology technology) {
        return this.technologyRepository.save(technology);
    }

    @Override
    public Map<String, List<Course>> convertList(List<Technology> list) {
        // sort by key, use case-insensitive order
        Map<String, List<Course>> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        // deep copy of list
        List<Technology> copyArray = new ArrayList<>(list.size());

        for (Technology technology : list) {
            copyArray.add(new Technology(technology));
        }

        for (Technology technology : copyArray) {
            map.put(technology.getName(), technology.getCourses());

            // sort by course name
            technology.getCourses().sort(Comparator.comparing(Course::getName));
        }

        return map;
    }

    @Override
    public Optional<Technology> findTechnologyByName(String name) {
        return technologyRepository.findByName(name);
    }

    @Override
    public Technology updateTechnology(Technology technology, Integer technologyId) throws TechnologyNotFoundException {
        if (technologyRepository.findById(technologyId).isPresent()){
            return this.technologyRepository.findById(technologyId).map(tech -> {
                tech.setName(technology.getName());
                return technologyRepository.save(tech);
            }).orElseThrow(
                    () -> new TechnologyNotFoundException("COULD NOT FIND TECHNOLOGY " + technology.getTechnologyId()));
        }
        return null;
    }

    @Override
    public List<Technology> findAllTechnology() {
        return this.technologyRepository.findAll();
    }

    @Override
    public boolean deleteTechnologyByName(String technologyName) throws TechnologyNotFoundException {
        return this.technologyRepository.findByName(technologyName).map(tech -> {
            this.technologyRepository.delete(tech);
            return true;
        }).orElseThrow(() -> new TechnologyNotFoundException("NO RECORD FOUND FOR TECHNOLOGY " + technologyName));
    }

}
