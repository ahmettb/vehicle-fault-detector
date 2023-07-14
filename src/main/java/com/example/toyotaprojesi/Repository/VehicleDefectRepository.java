package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.VehicleDefect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDefectRepository extends JpaRepository<VehicleDefect,Long> {
}
