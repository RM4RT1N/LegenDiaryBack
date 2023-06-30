package com.codecool.el_grande_project.controller;

import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.entity.City;
import com.codecool.el_grande_project.entity.Image;
import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.service.CityService;
import com.codecool.el_grande_project.service.ImageService;
import com.codecool.el_grande_project.service.PlaceService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@CrossOrigin("*")
public class ApiController {
    private final PlaceService placeService;
    private final CityService cityService;
    private final ImageService imageService;


    public ApiController(PlaceService placeService, CityService cityService, ImageService imageService) {
        this.placeService = placeService;
        this.cityService = cityService;
        this.imageService = imageService;
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
    @GetMapping("/images")
    @CrossOrigin("*")
    @ResponseBody
    public List<Image> getAllImages() {
        return imageService.getImages();

    }
    @GetMapping("/images/{legendId}")
    @CrossOrigin("*")
    @ResponseBody
    public List<Image> getImagesByLegendId(@PathVariable String legendId) {
        return imageService.getImagesByLegendId(Long.valueOf(legendId));
    }
}
