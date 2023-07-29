package com.example.toyotaprojesi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
@Column(name = "image")
private    byte[]image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}
