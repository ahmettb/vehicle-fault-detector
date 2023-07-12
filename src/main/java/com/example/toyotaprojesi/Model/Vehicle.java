package com.example.toyotaprojesi.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vehicleId;

    @Column(name = "vehicleName")
    String vehicleName;
    @Column(name = "vehicleModel")
    String vehicleModel;

    @ManyToMany
    @JoinTable(name = "vehicle_defects", joinColumns = @JoinColumn(name = "vehicleId"), inverseJoinColumns = @JoinColumn(name = "id"))
    List<VehicleDefect> vehicleDefects = new ArrayList<>();

}
