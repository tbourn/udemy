package com.thomas.wishlist.repository;

import com.thomas.wishlist.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

    Optional<Technology> findByName(String name);

}
