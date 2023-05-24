package com.codecool.el_grande_project.service;

import com.codecool.el_grande_project.entity.City;

import com.codecool.el_grande_project.repository.CityRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getCities(){
        return this.cityRepository.findAll();
    }
    public City getCityByName(String name){
        return this.cityRepository.findByCity(name);
    }



}
