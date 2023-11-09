package com.codecool.el_grande_project.service;

import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.entity.City;
import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.mappers.PlaceMapper;
import com.codecool.el_grande_project.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import com.codecool.el_grande_project.util.CalculateDistance;
@Service
public class PlaceService {

    private final PlaceMapper placeMapper;
    private final PlaceRepository placeRepository;
    private final CityService cityService;


    public PlaceService(PlaceRepository placeRepository, CityService cityService, PlaceMapper placeMapper) {
        this.placeRepository = placeRepository;
        this.cityService = cityService;
        this.placeMapper = placeMapper;

    }

    public List<PlaceDTO> getPlaces(){
        List<Place> places = this.placeRepository.findAll();
        return places.stream().filter(Place::isApproved).map(placeMapper::mapPlaceToDTO).toList();
    }
    public List<PlaceDTO> getPlacesToAccepted(){
        List<Place> places = this.placeRepository.findAll();
        return places.stream().filter(place -> !place.isApproved()).map(placeMapper::mapPlaceToDTO).toList();
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


    public Place getPlaceById(UUID id) {
        Optional<Place> optionalPlace = placeRepository.findById(id);
        if (optionalPlace.isPresent()) {
            return optionalPlace.get();
        } else {
            throw new NoSuchElementException("Place with ID " + id + " does not exist");
        }
    }
    public void updatePlace(Place newDataPlace){
        Place placeToEdit = getPlaceById(newDataPlace.getId());
        if (placeToEdit != null){
            placeToEdit.setName(newDataPlace.getName());
            placeToEdit.setDescription(newDataPlace.getDescription());
            placeToEdit.setLatitude(newDataPlace.getLatitude());
            placeToEdit.setLongitude(newDataPlace.getLongitude());
            placeToEdit.setCategory_id(newDataPlace.getCategory_id());
            placeToEdit.setApproved(false);
        }
        else{
            throw new NoSuchElementException("Place with ID " + newDataPlace.getId() + " does not exist");
        }
        placeRepository.save(placeToEdit);

    }
    public void AcceptPlace(Place newDataPlace){
        Place placeToEdit = getPlaceById(newDataPlace.getId());
        if (placeToEdit != null){
            placeToEdit.setApproved(true);
        }
        else{
            throw new NoSuchElementException("Place with ID " + newDataPlace.getId() + " does not exist");
        }
        placeRepository.save(placeToEdit);

    }
}
