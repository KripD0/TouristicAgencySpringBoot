package com.example.touristicagency.model;

import lombok.Data;

@Data
public class ImageTour {

    private int id;
    private byte[] content;
    private int tourId;
}
