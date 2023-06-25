package com.codecool.el_grande_project.controller;

import com.codecool.el_grande_project.DTO.NewPlaceDTO;
import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.entity.City;
import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.entity.UserEntity;
import com.codecool.el_grande_project.repository.UserRepository;
import com.codecool.el_grande_project.service.CityService;
import com.codecool.el_grande_project.service.PlaceService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class ApiController {
    private final PlaceService placeService;
    private final CityService cityService;
    private final UserRepository userRepository;


    public ApiController(PlaceService placeService, CityService cityService, UserRepository userRepository) {
        this.placeService = placeService;
        this.cityService = cityService;
        this.userRepository = userRepository;
    }

    @GetMapping("/places")
    @ResponseBody
    public List<PlaceDTO> getAllPlaces() {
        return placeService.getPlaces();

    }
    @GetMapping("/placesContainWord")
    @ResponseBody
    public List<Place> getPlacesContainWord(@RequestParam String word) {
        return placeService.getPlacesContainWord(word);

    }
    @GetMapping("/placeInCity")
    @ResponseBody
    public List<Place> getCityPlaces(@RequestParam String city) {
        return placeService.getPlaceNearCity(city);
    }
    @GetMapping("/placeNearby")
    @ResponseBody
    public List<Place> getNearbyPlaces(@RequestParam double lat,double lng,double distance) {
        return placeService.getNearbyPlaces(lat,lng,distance);
    }
    @GetMapping("/cities")
    @ResponseBody
    public List<City> getAllCities() {
        return cityService.getCities();

    }
    @PostMapping("/api/add-legend")
    public String saveLegend(@RequestBody NewPlaceDTO newPlaceDto) {
        UserEntity user = userRepository.findById(newPlaceDto.getUserId()).get();
        this.placeService.addPlace(new Place(
                user,
                newPlaceDto.getCategory_id(),
                newPlaceDto.getLatitude(),
                newPlaceDto.getLongitude(),
                newPlaceDto.getDescription(),
                newPlaceDto.getName()));
        return "OK";
    }
    @GetMapping("/place/{id}")
    @ResponseBody
    public Place onePlace(@PathVariable("id") UUID id) {
        return this.placeService.getPlaceById(id);
    }
    @PatchMapping("/api/edit-legend/{id}")

    public String updateLegend(@PathVariable("id") UUID id, @RequestBody NewPlaceDTO newPlaceDto) {
        Place existingPlace = onePlace(id);

        if (existingPlace == null) {
            return "Obiekt o podanym identyfikatorze nie istnieje";
        }

        existingPlace.setCategory_id(newPlaceDto.getCategory_id());
        existingPlace.setLatitude(newPlaceDto.getLatitude());
        existingPlace.setLongitude(newPlaceDto.getLongitude());
        existingPlace.setDescription(newPlaceDto.getDescription());
        existingPlace.setName(newPlaceDto.getName());

        this.placeService.updatePlace(existingPlace);

        return "OK";
    }
}
