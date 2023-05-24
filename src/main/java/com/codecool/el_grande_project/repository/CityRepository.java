package com.codecool.el_grande_project.repository;

import com.codecool.el_grande_project.entity.City;
import com.codecool.el_grande_project.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Long> {
    City findByCity(String name);
}
