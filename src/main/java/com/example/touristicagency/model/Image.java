package com.example.touristicagency.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "data", columnDefinition = "bytea")
    private byte[] data;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_id")
    private Tour tour;
}
