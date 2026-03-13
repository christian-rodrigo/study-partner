package com.example.backend.repository;

import com.example.backend.entity.LearningPlace;
import com.example.backend.enums.City;
import com.example.backend.enums.LearningPlaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LearningPlaceRepository extends JpaRepository<LearningPlace,Long> {
    List<LearningPlace> findByType(LearningPlaceType type);
    List<LearningPlace> findByCity(City City);
}
