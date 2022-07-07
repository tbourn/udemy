package com.thomas.wishlist.service;

import com.thomas.wishlist.entity.Course;
import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.TechnologyNotFoundException;

import java.util.List;
import java.util.Map;

public interface TechnologyService {
    Technology createTechnology(Technology technology);

    Technology findById(Integer technologyId) throws TechnologyNotFoundException;

    Map<String, List<Course>> convertList(List<Technology> list);

    Technology updateTechnology(Technology technology, Integer id) throws TechnologyNotFoundException;

    List<Technology> findAllTechnology();

    boolean deleteTechnologyById(Integer technologyId) throws TechnologyNotFoundException;

    boolean deleteTechnologyByName(String name) throws TechnologyNotFoundException;
}
