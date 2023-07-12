package com.example.toyotaprojesi.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VehicleDefect")
public class VehicleDefect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "defectName")
    String defectName;
    @Column(name = "defectX")
    int defectX;
    @Column(name = "defectY")
    int defectY;

    @ManyToMany(mappedBy = "vehicleDefects")
    List<Vehicle> vehicles = new ArrayList<>();
}
