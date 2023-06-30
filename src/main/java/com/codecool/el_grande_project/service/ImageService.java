package com.codecool.el_grande_project.service;

import com.codecool.el_grande_project.entity.City;
import com.codecool.el_grande_project.entity.Image;
import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getImages(){
        return this.imageRepository.findAll();
    }

    public List<Image> getImagesByLegendId(Long legendId){
        return this.imageRepository.findAll().stream()
                .filter(p->p.getPlace_id()==(legendId))
                .collect(Collectors.toList());
    }

    public void addImage(Image image){
        this.imageRepository.save(image);
    }


}
