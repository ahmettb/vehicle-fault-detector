package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.Defect;
import com.example.toyotaprojesi.Model.DefectLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDefectLocationRepo extends JpaRepository<DefectLocation,Long> {


    List<DefectLocation> findByDefect(Defect defect);
}
