package com.codecool.el_grande_project.service;

import com.codecool.el_grande_project.entity.City;
import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import com.codecool.el_grande_project.util.CalculateDistance;
@Service
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final CityService cityService;


    public PlaceService(PlaceRepository placeRepository, CityService cityService) {
        this.placeRepository = placeRepository;
        this.cityService = cityService;

    }

    public List<Place> getPlaces(){
        return this.placeRepository.findAll();
    }

    public List<Place> getNearbyPlaces(double lat, double lng, double distance) {
        return this.placeRepository.findAll().stream()
                .filter(p -> CalculateDistance.isPlaceInRange(
                        lat, lng, p.getLatitude(), p.getLongitude(), distance))
                .collect(Collectors.toList());
    }


    public List<Place> getPlacesContainWord(String word){
        var wordUpper = word.toUpperCase();
        return this.placeRepository.findAll().stream()
                .filter(p->p.getName().toUpperCase().contains(wordUpper) || p.getDescription().toUpperCase().contains(wordUpper)
                )
                .collect(Collectors.toList());}
    public List<Place> getPlaceNearCity(String nameCity){
        City city = cityService.getCityByName(nameCity);
        return this.placeRepository.findAll().stream()
                .filter(p->p.getLongitude()==(city.getLongitude())
                        &&p.getLatitude()==(city.getLatitude())
                )
                .collect(Collectors.toList());
    }
    public void addPlace(Place place){
        this.placeRepository.save(place);
    }


}