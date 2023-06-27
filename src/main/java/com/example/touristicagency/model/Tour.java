package com.example.touristicagency.model;

import lombok.Data;

@Data
public class Tour {

    private int id;
    private String name;
    private String country;
    private String town;
    private int days;
    private String cost;
    private String description;
}
