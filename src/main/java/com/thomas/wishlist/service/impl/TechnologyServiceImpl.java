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

        boolean isEqual = list.equals(copyArray);
        System.out.println(isEqual);

        return map;
    }

    @Override
    public Technology updateTechnology(Technology technology, Integer technologyId) throws TechnologyNotFoundException {
        return this.technologyRepository.findById(technologyId).map(tech -> {
            tech.setName(technology.getName());
            return technologyRepository.save(tech);
        }).orElseThrow(
                () -> new TechnologyNotFoundException("Could not find department " + technology.getTechnologyId()));
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
        }).orElseThrow(() -> new TechnologyNotFoundException("No record found for department " + technologyName));
    }

}
