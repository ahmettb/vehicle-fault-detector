package com.example.toyotaprojesi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class VehicleDefectResponse {

@JsonIgnore
    private VehicleDefect vehicleDefect;

    public VehicleDefect getVehicleDefect() {
        return vehicleDefect;
    }

    public void setVehicleDefect(VehicleDefect vehicleDefect) {
        this.vehicleDefect = vehicleDefect;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    private Vehicle vehicle;

    private List<Defect>defects;


    private String marka;
    private String model;
    private int yil;
    private String renk;

    public String getHataAdi() {
        return hataAdi;
    }

    public void setHataAdi(String hataAdi) {
        this.hataAdi = hataAdi;
    }




    private String hataAdi;
    private int Xkoordinat;
    private int Ykoordinat;

    public int getXkoordinat() {
        return Xkoordinat;
    }

    public void setXkoordinat(int xkoordinat) {
        Xkoordinat = xkoordinat;
    }

    public int getYkoordinat() {
        return Ykoordinat;
    }

    public void setYkoordinat(int ykoordinat) {
        Ykoordinat = ykoordinat;
    }

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

//    public List<VehicleDefect> getVehicleDefectList() {
//        return vehicleDefectList;
//    }
//
//    public void setVehicleDefectList(List<VehicleDefect> vehicleDefectList) {
//        this.vehicleDefectList = vehicleDefectList;
//    }
//
//    public List<DefectLocation> getVehicDefectLocations() {
//        return vehicDefectLocations;
//    }
//
//    public void setVehicDefectLocations(List<DefectLocation> vehicDefectLocations) {
//        this.vehicDefectLocations = vehicDefectLocations;
//
//    }
//
//    public Vehicle getVehicle() {
//        return vehicle;
//    }
//
//    public List<DefectLocation> getDefectLocation() {
//        return defectLocation;
//    }
//
//    public void setDefectLocation(List<DefectLocation> defectLocation) {
//        this.defectLocation = defectLocation;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
//
//    private Vehicle vehicle;
//
//    private List< DefectLocation> defectLocation;
//
//    List<VehicleDefect> vehicleDefectList;
//
//    List<DefectLocation>vehicDefectLocations;

}
