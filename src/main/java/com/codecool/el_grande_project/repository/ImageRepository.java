package com.codecool.el_grande_project.repository;

import com.codecool.el_grande_project.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
