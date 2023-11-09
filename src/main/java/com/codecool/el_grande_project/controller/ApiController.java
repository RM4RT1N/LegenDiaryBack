package com.codecool.el_grande_project.controller;

import com.codecool.el_grande_project.DTO.NewPlaceDTO;
import com.codecool.el_grande_project.DTO.PlaceDTO;
import com.codecool.el_grande_project.entity.City;
import com.codecool.el_grande_project.entity.Image;
import com.codecool.el_grande_project.entity.Place;
import com.codecool.el_grande_project.entity.UserEntity;
import com.codecool.el_grande_project.repository.UserRepository;
import com.codecool.el_grande_project.service.CityService;
import com.codecool.el_grande_project.service.PlaceService;
import com.codecool.el_grande_project.service.ImageService;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class ApiController {
    private final PlaceService placeService;
    private final CityService cityService;
    private final UserRepository userRepository;
    private final ImageService imageService;


    public ApiController(PlaceService placeService, CityService cityService, UserRepository userRepository,ImageService imageService) {
        this.placeService = placeService;
        this.cityService = cityService;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @GetMapping("/places")
    @ResponseBody
    public List<PlaceDTO> getAllPlaces() {
        return placeService.getPlaces();

    }

    @GetMapping("/placesToAccept")
    @ResponseBody
    public List<PlaceDTO> getPlacesToAccepted() {
        return placeService.getPlacesToAccepted();

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
        Place place = new Place(
                user,
                newPlaceDto.getCategory_id(),
                newPlaceDto.getLatitude(),
                newPlaceDto.getLongitude(),
                newPlaceDto.getDescription(),
                newPlaceDto.getName(),
                newPlaceDto.isApproved());


        this.placeService.addPlace(place);
        newPlaceDto.getImageUrls().forEach(image->{
            this.imageService.addImage(new Image(place.getId(),image));
        });

        return "OK";
    }
    @GetMapping("/place/{id}")
    @ResponseBody
    public Place onePlace(@PathVariable("id") UUID id) {
        return this.placeService.getPlaceById(id);
    }
    @PatchMapping("/api/accept-legend/{id}")
    public String AcceptPlace (@PathVariable("id") UUID id, @RequestBody NewPlaceDTO newPlaceDto) {
        Place existingPlace = onePlace(id);

        if (existingPlace == null) {
            return "Obiekt o podanym identyfikatorze nie istnieje";
        }

        this.placeService.AcceptPlace(existingPlace);

        return "OK";
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
    @GetMapping("/images")
    @CrossOrigin("*")
    @ResponseBody
    public List<Image> getAllImages() {
        return imageService.getImages();

    }
    @GetMapping("/images/{legendId}")
    @CrossOrigin("*")
    @ResponseBody
    public List<Image> getImagesByLegendId(@PathVariable UUID legendId) {
        return imageService.getImagesByLegendId(legendId);
    }
}
