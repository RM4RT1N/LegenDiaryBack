package com.codecool.el_grande_project.controller;

import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.entity.City;
import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.service.CityService;
import com.codecool.el_grande_project.service.PlaceService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@CrossOrigin("*")
public class ApiController {
    private final PlaceService placeService;
    private final CityService cityService;


    public ApiController(PlaceService placeService, CityService cityService) {
        this.placeService = placeService;
        this.cityService = cityService;
    }

    @GetMapping("/places")
    @ResponseBody
    public List<Place> getAllPlaces() {
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
    public String saveLegend(@RequestBody PlaceDTO placeDto) {
        this.placeService.addPlace(new Place(null,1L,
                placeDto.getCategory_id(),
                placeDto.getLatitude(),
                placeDto.getLongitude(),
                placeDto.getDescription(),
                placeDto.getName()));
        return "OK";
    }
    @GetMapping("/place/{id}")
    @ResponseBody
    public Place onePlace(@PathVariable("id") Long id) {
        return this.placeService.getPlaceById(id);
    }
    @PatchMapping("/api/edit-legend/{id}")

    public String updateLegend(@PathVariable("id") Long id, @RequestBody PlaceDTO placeDto) {
        Place existingPlace = onePlace(id);

        if (existingPlace == null) {
            return "Obiekt o podanym identyfikatorze nie istnieje";
        }

        existingPlace.setCategory_id(placeDto.getCategory_id());
        existingPlace.setLatitude(placeDto.getLatitude());
        existingPlace.setLongitude(placeDto.getLongitude());
        existingPlace.setDescription(placeDto.getDescription());
        existingPlace.setName(placeDto.getName());

        this.placeService.updatePlace(existingPlace);

        return "OK";
    }
}
