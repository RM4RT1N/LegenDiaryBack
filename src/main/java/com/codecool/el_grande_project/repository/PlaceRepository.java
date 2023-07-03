package com.codecool.el_grande_project.repository;

import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlaceRepository extends JpaRepository<Place, UUID> {
    List<Place> findPlaceByUser(UserEntity user);
}
