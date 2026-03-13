package com.example.backend.controller;

import com.example.backend.entity.LearningPlace;
import com.example.backend.enums.City;
import com.example.backend.enums.LearningPlaceType;
import com.example.backend.service.LearningPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning-places")
public class LearningPlaceController {

    @Autowired
    private LearningPlaceService service;

    @GetMapping
    public List<LearningPlace> getAll() {
        return service.getAllPlaces();
    }

    @GetMapping("/search")
    public List<LearningPlace> search(@RequestParam(required = false) City city,
                                      @RequestParam(required = false) LearningPlaceType type){

        return service.searchPlaces(city,type);
    }

    @PostMapping
    public LearningPlace create(@RequestBody LearningPlace place){
        return service.createPlace(place);
    }


}