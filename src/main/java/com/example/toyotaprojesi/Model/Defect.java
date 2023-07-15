package com.example.toyotaprojesi.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Defect")
public class Defect {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

@Column(name = "defectName")
private String defectName;

@Column(name = "deleted")
private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }
}
