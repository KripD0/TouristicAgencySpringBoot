package com.example.touristicagency.service;

import com.example.touristicagency.model.Image;
import com.example.touristicagency.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Image findById(int tourId) {
        return imageRepository.findByTourId(tourId).orElseThrow();
    }
}