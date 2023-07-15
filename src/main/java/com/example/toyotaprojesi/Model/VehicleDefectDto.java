package com.example.toyotaprojesi.Model;

public class VehicleDefectDto {

    private String marka;

    private String modelNo;
    private String model;

    private int yil;

    public String getMarka() {
        return marka;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
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

    public String getHataAdi() {
        return hataAdi;
    }

    public void setHataAdi(String hataAdi) {
        this.hataAdi = hataAdi;
    }

    public int getxKoordinati() {
        return xKoordinati;
    }

    public void setxKoordinati(int xKoordinati) {
        this.xKoordinati = xKoordinati;
    }

    public int getyKoordinati() {
        return yKoordinati;
    }

    public void setyKoordinati(int yKoordinati) {
        this.yKoordinati = yKoordinati;
    }

    private String renk;

    private String hataAdi;

    private int xKoordinati;

    private int yKoordinati;

}
