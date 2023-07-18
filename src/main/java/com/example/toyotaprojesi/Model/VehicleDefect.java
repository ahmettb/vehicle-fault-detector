package com.example.toyotaprojesi.Model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import org.hibernate.annotations.Type;

import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "vehicle_defect")
public class VehicleDefect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "defect_id", nullable = false)
    private Defect defect;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "resim_id", nullable = false)
    private Image image;









}


// getters and setters