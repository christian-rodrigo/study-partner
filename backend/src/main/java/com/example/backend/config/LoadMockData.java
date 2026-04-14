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
            repo.save(new LearningPlace(null, "TU Dortmund Library", "Emil-Figge-Str. 50", City.DORTMUND, LearningPlaceType.LIBRARY,
                    "https://images.unsplash.com/photo-1495446815901-a7297e633e8d?w=400&h=300&fit=crop"));

            repo.save(new LearningPlace(null, "Campus Café West", "Universitätsstraße 150", City.DORTMUND, LearningPlaceType.CAFE,
                    "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?w=400&h=300&fit=crop"));

            repo.save(new LearningPlace(null, "Hamburg CoWorking Space", "Tech Street 7", City.HAMBURG, LearningPlaceType.COWORKING,
                    "https://images.unsplash.com/photo-1497366754035-f200968a6e72?w=400&h=300&fit=crop"));

            repo.save(new LearningPlace(null, "Cologne City Library", "Neumarkt 3", City.COLOGNE, LearningPlaceType.LIBRARY,
                    "https://images.unsplash.com/photo-1512820790803-83ca734da794?w=400&h=300&fit=crop"));

            repo.save(new LearningPlace(null, "Study Café Rheinblick", "Rheinpromenade 12", City.COLOGNE, LearningPlaceType.CAFE,
                    "https://images.unsplash.com/photo-1466978913421-dad2ebd01d17?w=400&h=300&fit=crop"));
           };
    }

}
