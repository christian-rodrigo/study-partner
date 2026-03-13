package com.example.backend.service;

import com.example.backend.entity.LearningPlace;
import com.example.backend.enums.City;
import com.example.backend.enums.LearningPlaceType;
import com.example.backend.repository.LearningPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class LearningPlaceService {

    @Autowired
    private LearningPlaceRepository repository;


        public List<LearningPlace> getAllPlaces() {
            return repository.findAll();
        }

        public List<LearningPlace> searchPlaces(City city, LearningPlaceType type){
            if(city != null && type != null){
                return repository.findByCity(city).stream()
                        .filter(p->p.getType()==type)
                        .toList();
            } else if (city != null) {
                return repository.findByCity(city);
            } else if (type != null) {
                return repository.findByType(type);
            }else {
                return repository.findAll();
            }

        }

        public LearningPlace createPlace(LearningPlace place){
            return repository.save(place);
        }
}

