package com.example.touristicagency.dao;

import com.example.touristicagency.model.ImageTour;
import com.example.touristicagency.model.Tour;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TourDAO {

    private final JdbcTemplate jdbcTemplate;

    public TourDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tour> getAllTours(){
        return jdbcTemplate.query("SELECT * FROM tours", new BeanPropertyRowMapper<>(Tour.class));
    }

    public void createTour(Tour tour, MultipartFile image) throws IOException {
        jdbcTemplate.update("INSERT INTO tours(name, country, town, days, cost, description) VALUES (?, ?, ?, ?, ?, ?)",
                tour.getName(), tour.getCountry(), tour.getTown(), tour.getDays(), tour.getCost(), tour.getDescription());
        jdbcTemplate.update("INSERT INTO imagetour(content, tour_id) VALUES (?, (SELECT id FROM tours WHERE name = ?))", image.getBytes(), tour.getName());
    }

    public Tour getTourByID(int id){
        return jdbcTemplate.query("SELECT * FROM tours WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Tour.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public ImageTour getImageByTourID(int tourID){
        return jdbcTemplate.query("SELECT * FROM imagetour WHERE tour_id = ?", new Object[]{tourID}, new BeanPropertyRowMapper<>(ImageTour.class))
                .stream()
                .findAny()
                .orElse(null);
    }
}
