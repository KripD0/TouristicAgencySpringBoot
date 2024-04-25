package com.example.touristicagency.repository;

import com.example.touristicagency.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByTourId(int tourId);
}