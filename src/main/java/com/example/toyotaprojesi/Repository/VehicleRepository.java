package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


    @Query(value = "SELECT CASE WHEN COUNT(vehicle)>0 THEN true ELSE false END FROM vehicle  WHERE vehicle.model_no = :modelno", nativeQuery = true)
   Boolean findByModelNo(@Param("modelno")String modelno);

    @Query(value = "SELECT * FROM vehicle  WHERE vehicle.model_no = :modelno", nativeQuery = true)
    Optional<Vehicle> findByModel(@Param("modelno")String modelno);

    @Query(value = "SELECT * FROM vehicle  WHERE vehicle.deleted = false", nativeQuery = true)
    List<Vehicle> activeVehicles();

    @Query(value = "SELECT * FROM vehicle  WHERE vehicle.deleted = false and vehicle.id = :id", nativeQuery = true)
    Vehicle activeVehicleById(@Param("id")long id);

    @Query(value = "SELECT * FROM vehicle  WHERE vehicle.deleted = false and vehicle.model_no = :modelno", nativeQuery = true)
    Vehicle activeVehicleByModelNo(@Param("modelno")String modelno);



}
