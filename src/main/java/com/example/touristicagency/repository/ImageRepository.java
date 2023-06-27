package com.example.touristicagency.repository;

import com.example.touristicagency.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findByTourId(int tourId);
}
