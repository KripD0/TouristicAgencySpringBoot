package com.example.touristicagency.controllers;

import com.example.touristicagency.dao.TourDAO;
import com.example.touristicagency.model.Tour;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/hotTours")
public class TourController {

    private final TourDAO tourDAO;

    public TourController(TourDAO tourDAO) {
        this.tourDAO = tourDAO;
    }

    @GetMapping
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/aboutUs")
    public String getAboutUsPage(){
        return "aboutUs";
    }

    @GetMapping("/tours")
    public String getToursPage(Model model){
        List<Tour> tours = tourDAO.getAllTours();
        model.addAttribute("tours", tours);
        return "tours";
    }

    @GetMapping("/tours/{id}")
    public String getCurrentTour(@PathVariable("id") int id, Model model){
        model.addAttribute("tour", tourDAO.getTourByID(id));
        model.addAttribute("image", tourDAO.getImageByTourID(id));
        return "currentTour";
    }

    @GetMapping("/add-tour")
    public String createTourPage(@ModelAttribute("tour") Tour tour){
        return "addTour";
    }

    @PostMapping
    public String createNewTour(@ModelAttribute("tour") Tour tour, @RequestParam("image")MultipartFile image){
        try {
            tourDAO.createTour(tour, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/hotTours/tours";
    }
}
