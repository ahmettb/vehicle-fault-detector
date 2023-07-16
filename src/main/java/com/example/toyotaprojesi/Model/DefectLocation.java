package com.example.toyotaprojesi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "defect_location")
public class DefectLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "defect_id", nullable = false)
    //@JsonIgnore
    private Defect defect;



    public Defect getDefect() {
        return defect;
    }

    public void setDefect(Defect defect) {
        this.defect = defect;
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

    private int xKoordinati;

    private int yKoordinati;

    // getters and setters
}