package com.example.toyotaprojesi.Service;


import com.example.toyotaprojesi.Model.*;
import com.example.toyotaprojesi.Repository.DefectRepository;
import com.example.toyotaprojesi.Repository.VehicleDefectLocationRepo;
import com.example.toyotaprojesi.Repository.VehicleDefectRepository;
import com.example.toyotaprojesi.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {



    @Autowired
    DefectRepository defectRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleDefectLocationRepo vehicleDefectLocationRepo;


    @Autowired
    VehicleDefectRepository vehicleDefectRepository;


    public void vehicleAdd(VehicleDefectDto vehicleDefectDto) {

       // if (vehicleRepository.findByModelNo(vehicleDefectDto.getModel())) {
        //    return;
       // }

        Vehicle vehicle = new Vehicle();
        vehicle.setModel(vehicleDefectDto.getModel());
        vehicle.setMarka(vehicleDefectDto.getMarka());
        vehicle.setRenk(vehicleDefectDto.getRenk());
        vehicle.setYil(vehicleDefectDto.getYil());
        vehicleRepository.save(vehicle);


        Defect defect = new Defect();
        defect.setDefectName(vehicleDefectDto.getHataAdi());

        defectRepository.save(defect);


        DefectLocation defectLocation = new DefectLocation();
        defectLocation.setDefect(defect);
        defectLocation.setxKoordinati(vehicleDefectDto.getxKoordinati());
        defectLocation.setyKoordinati(vehicleDefectDto.getyKoordinati());

        vehicleDefectLocationRepo.save(defectLocation);

        VehicleDefect vehicleDefect = new VehicleDefect();

        vehicleDefect.setVehicle(vehicle);
        vehicleDefect.setDefect(defect);
        vehicleDefectRepository.save(vehicleDefect);


    }


}
