package com.example.touristicagency.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tours")
@Data
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "town")
    private String town;

    @Column(name = "days")
    private int days;

    @Column(name = "cost")
    private String cost;

    @Column(name = "description")
    private String description;
}
