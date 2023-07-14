package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.DefectLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDefectLocationRepo extends JpaRepository<DefectLocation,Long> {
}
