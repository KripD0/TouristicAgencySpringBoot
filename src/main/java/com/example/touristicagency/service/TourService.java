package com.example.touristicagency.service;

import com.example.touristicagency.model.Image;
import com.example.touristicagency.model.Tour;
import com.example.touristicagency.repository.ImageRepository;
import com.example.touristicagency.repository.TourRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TourService {
    private TourRepository tourRepository;
    private ImageRepository imageRepository;
    public TourService(TourRepository tourRepository, ImageRepository imageRepository) {
        this.tourRepository = tourRepository;
        this.imageRepository = imageRepository;
    }

    public List<Tour> findAll(){
        return tourRepository.findAll();
    }

    @Transactional
    public void createTour(Tour tour, MultipartFile loadedImage) throws IOException {
        Image image = new Image();
        byte[] data = loadedImage.getBytes();
        image.setData(data);
        image.setName(loadedImage.getOriginalFilename());
        image.setTour(tour);
        imageRepository.save(image);
    }

    public Tour findById(int id){
        return tourRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteTour(int id){
        imageRepository.delete(imageRepository.findByTourId(id));
    }

    @Transactional
    public void updateTour(Tour tour, MultipartFile loadedImage, int id) throws IOException {
        tour.setId(id);
        tourRepository.save(tour);
        if(!loadedImage.isEmpty()){
            Image image = imageRepository.findByTourId(tour.getId());
            image.setData(loadedImage.getBytes());
            image.setName(loadedImage.getName());
            imageRepository.save(image);
        }
    }
}
