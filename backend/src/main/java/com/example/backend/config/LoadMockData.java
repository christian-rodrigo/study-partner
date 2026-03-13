package com.example.backend.config;

import com.example.backend.entity.LearningPlace;
import com.example.backend.enums.City;
import com.example.backend.enums.LearningPlaceType;
import com.example.backend.repository.LearningPlaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadMockData {

    @Bean
    CommandLineRunner initLearningPlaces(LearningPlaceRepository repo){
        return args -> {
            repo.save(new LearningPlace(null, "Central Library", "Main Street 1", City.BERLIN, LearningPlaceType.LIBRARY, "https://via.placeholder.com/300x200?text=Central+Library"));
            repo.save(new LearningPlace(null, "Cafe Mocha", "Coffee Lane 5", City.BERLIN, LearningPlaceType.CAFE, "https://via.placeholder.com/300x200?text=Cafe+Mocha"));
        };
    }

}
