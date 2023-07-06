package com.codecool.el_grande_project.repository;

import com.codecool.el_grande_project.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}