package com.example.toyotaprojesi.Model;

import jakarta.persistence.*;
import org.hibernate.boot.model.internal.XMLContext;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


@Column(name ="marka" )
    private String marka;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name="deleted")
boolean deleted= false;

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    @Column(name = "modelNo")
private String modelNo;

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    @Column(name = "model")
    private String model;
    @Column(name ="yil" )

    private int yil;
    @Column(name = "renk")

    private String renk;

    // getters and setters
}