package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {


  //  @Query(value = "SELECT CASE WHEN COUNT(vehicle)>0 THEN true ELSE false END FROM vehicle  WHERE vehicle.vehicle_model_no = :modelno", nativeQuery = true)
   // Boolean findByModelNo(@Param("modelno")String modelNo);

}
