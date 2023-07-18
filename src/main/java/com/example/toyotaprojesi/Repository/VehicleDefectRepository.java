package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.Vehicle;
import com.example.toyotaprojesi.Model.VehicleDefect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDefectRepository extends JpaRepository<VehicleDefect, Long> {

    @Query(value = "SELECT resim FROM vehicle_defect  WHERE vehicle_defect.vehicle_id = :id", nativeQuery = true)
    List<byte[]> getImgs(@Param("id") long id);

    @Query(value = "SELECT resim FROM images  WHERE vehice_defect. = :id", nativeQuery = true)
    byte[] findImageByDefectId(@Param("id")long id);

    List<VehicleDefect> findByVehicle(Vehicle vehicle);

}
