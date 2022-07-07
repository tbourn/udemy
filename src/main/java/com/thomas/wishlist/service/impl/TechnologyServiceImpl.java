package com.thomas.wishlist.service.impl;

import com.thomas.wishlist.entity.Technology;
import com.thomas.wishlist.exception.TechnologyNotFoundException;
import com.thomas.wishlist.repository.TechnologyRepository;
import com.thomas.wishlist.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    public Technology createTechnology(Technology technology) {
        return this.technologyRepository.save(technology);
    }

    @Override
    public Technology findById(Integer technologyId) throws TechnologyNotFoundException {
        return this.technologyRepository.findById(technologyId)
                .orElseThrow(() -> new TechnologyNotFoundException("Could not find department " + technologyId));
    }

//    @Override
//    public Technology findByName(String technologyName) throws TechnologyNotFoundException {
//        return technologyRepository.findByName(technologyName)
//                .orElseThrow(() -> new TechnologyNotFoundException("Technology Not Found with technologyName:" + technologyName));
//    }

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
    public boolean deleteTechnologyById(Integer technologyId) throws TechnologyNotFoundException {
        return this.technologyRepository.findById(technologyId).map(tech -> {
            this.technologyRepository.delete(tech);
            return true;
        }).orElseThrow(() -> new TechnologyNotFoundException("No record found for department " + technologyId));
    }

    @Override
    public boolean deleteTechnologyByName(String technologyName) throws TechnologyNotFoundException {
        return this.technologyRepository.findByName(technologyName).map(tech -> {
            this.technologyRepository.delete(tech);
            return true;
        }).orElseThrow(() -> new TechnologyNotFoundException("No record found for department " + technologyName));
    }

}
