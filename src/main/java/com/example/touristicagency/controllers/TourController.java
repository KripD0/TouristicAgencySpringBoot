package com.example.touristicagency.controllers;

import com.example.touristicagency.model.Tour;
import com.example.touristicagency.service.ImageService;
import com.example.touristicagency.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/hotTours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;
    private final ImageService imageService;

    @GetMapping
    public String getMainPage() {
        return "index";
    }

    @GetMapping("/aboutUs")
    public String getAboutUsPage() {
        return "aboutUs";
    }

    @GetMapping("/tours")
    public String getToursPage(Model model) {
        List<Tour> tours = tourService.findAll();
        model.addAttribute("tours", tours);
        return "tours";
    }

    @GetMapping("/tours/{id}")
    public String getCurrentTour(@PathVariable("id") int id, Model model) {
        model.addAttribute("tour", tourService.findById(id));
        String base64Image = Base64.getEncoder().encodeToString(imageService.findById(id).getData());
        model.addAttribute("imageData", base64Image);
        return "currentTour";
    }

    @GetMapping("/add-tour")
    @PreAuthorize("hasRole('ADMIN')")
    public String createTourPage(@ModelAttribute("tour") Tour tour) {
        return "addTour";
    }

    @PostMapping
    public String createNewTour(@ModelAttribute("tour") Tour tour, @RequestParam("image") MultipartFile image) {
        try {
            tourService.createTour(tour, image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/hotTours/tours";
    }

    @DeleteMapping("/tour/{id}")
    public String deleteTour(@PathVariable("id") int id) {
        tourService.deleteTour(id);
        return "redirect:/hotTours/tours";
    }

    @GetMapping("/contacts")
    public String getContactPage() {
        return "contacts";
    }

    @GetMapping("/tour/{id}/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("tour", tourService.findById(id));
        return "editTour";
    }

    @PatchMapping("/tour/{id}")
    public String editTour(@ModelAttribute("tour") Tour tour, @RequestParam("image") MultipartFile image, @PathVariable("id") int id) throws IOException {
        tourService.updateTour(tour, image, id);
        return "redirect:/hotTours/tours";
    }
}