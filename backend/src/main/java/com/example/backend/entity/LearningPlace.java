package com.example.backend.entity;

import com.example.backend.enums.City;
import com.example.backend.enums.LearningPlaceType;
import com.example.backend.repository.LearningPlaceRepository;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "learning_places")
public class LearningPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private City city;

    @Enumerated(EnumType.STRING)
    private LearningPlaceType type;// Library, Cafe, University, Coworking

    @Column(name = "image_url")
    private String imageUrl;   // static URL for MVP

    // Constructors
    public LearningPlace() {}
    public LearningPlace(Long id, String name, String address, City city, LearningPlaceType type, String imageUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.type = type;
        this.imageUrl = imageUrl;
    }
}
