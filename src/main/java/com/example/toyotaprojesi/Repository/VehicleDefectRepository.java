package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.Vehicle;
import com.example.toyotaprojesi.Model.VehicleDefect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDefectRepository extends JpaRepository<VehicleDefect,Long> {



List<VehicleDefect>findByVehicle(Vehicle vehicle);

}
