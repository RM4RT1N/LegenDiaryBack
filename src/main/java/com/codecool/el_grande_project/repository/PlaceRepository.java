package com.codecool.el_grande_project.repository;

import com.codecool.el_grande_project.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place,Long> {
}
