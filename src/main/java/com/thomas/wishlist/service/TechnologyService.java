package com.thomas.wishlist.service;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.TechnologyNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TechnologyService {
    Technology createTechnology(Technology technology);

    Map<String, List<Course>> convertList(List<Technology> list);

    Optional<Technology> findTechnologyByName(String name) throws TechnologyNotFoundException;

    Technology updateTechnology(Technology technology, Integer id) throws TechnologyNotFoundException;

    List<Technology> findAllTechnology();

    boolean deleteTechnologyByName(String name) throws TechnologyNotFoundException;
}
