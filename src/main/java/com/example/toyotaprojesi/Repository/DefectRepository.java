package com.example.toyotaprojesi.Repository;

import com.example.toyotaprojesi.Model.Defect;
import com.example.toyotaprojesi.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefectRepository extends JpaRepository<Defect,Long> {



    @Query(value = "SELECT * FROM defect  WHERE defect.deleted = false", nativeQuery = true)
    List<Defect> activeDefects();}
