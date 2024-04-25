package com.example.touristicagency.service;

import com.example.touristicagency.exceptions.ImageNotFoundException;
import com.example.touristicagency.model.Image;
import com.example.touristicagency.model.Tour;
import com.example.touristicagency.repository.ImageRepository;
import com.example.touristicagency.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;
    private final ImageRepository imageRepository;

    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Transactional
    public void createTour(Tour tour, MultipartFile loadedImage) throws IOException {
        Image image = new Image();
        image.setData(loadedImage.getBytes());
        image.setName(loadedImage.getOriginalFilename());
        image.setTour(tour);
        imageRepository.save(image);
    }

    public Tour findById(int id) {
        return tourRepository.findById(id).orElse(null);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTour(int id) {
        imageRepository.delete(imageRepository.findByTourId(id)
                .orElseThrow(() -> new ImageNotFoundException("Тур не найден")));
    }

    @Transactional
    public void updateTour(Tour tour, MultipartFile loadedImage, int id) throws IOException {
        tour.setId(id);
        tourRepository.save(tour);
        if (!loadedImage.isEmpty()) {
            Image image = imageRepository.findByTourId(tour.getId())
                    .orElseThrow(() -> new ImageNotFoundException("Тур не найден"));
            image.setData(loadedImage.getBytes());
            image.setName(loadedImage.getName());
            imageRepository.save(image);
        }
    }
}