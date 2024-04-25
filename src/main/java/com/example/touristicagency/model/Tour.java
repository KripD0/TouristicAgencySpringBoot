package com.example.touristicagency.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue
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
