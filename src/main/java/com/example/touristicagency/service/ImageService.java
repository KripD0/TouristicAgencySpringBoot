package com.example.touristicagency.service;

import com.example.touristicagency.model.Image;
import com.example.touristicagency.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ImageService {
    private ImageRepository imageRepository;
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image findById(int tourId){
        return imageRepository.findByTourId(tourId);
    }
}
