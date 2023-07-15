package com.example.toyotaprojesi.Controller;

import com.example.toyotaprojesi.Model.*;
import com.example.toyotaprojesi.Service.VehicleService;
import com.example.toyotaprojesi.exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("add")
    public ResponseEntity<VehicleDefectDto> vehicleAndDefectSave(@RequestBody VehicleDefectDto vehicleDefectDto) throws Exception {
        vehicleService.vehicleAdd(vehicleDefectDto);
        return new ResponseEntity<>(vehicleDefectDto, HttpStatus.CREATED);

    }

    @PostMapping("addDefect/{modelNo}")
    public ResponseEntity<VehicleDefectDto> addDefectByModelNo(@PathVariable("modelNo") String modelNo, @RequestBody VehicleDefectDto vehicleDefectDto) throws VehicleNotFoundException {
        return new ResponseEntity<>(vehicleService.addDefectToVehicleByModelNo(modelNo, vehicleDefectDto), HttpStatus.OK);
    }


    @GetMapping("getVehicleInfo/{id}")
    public ResponseEntity<List<VehicleDefectResponse>> getVehicleById(@PathVariable("id") long id) throws VehicleNotFoundException {
        return new ResponseEntity<>(vehicleService.getVehicleInfo(id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") long id) throws VehicleNotFoundException {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>("Delteed", HttpStatus.OK);
    }


    @GetMapping("getallVehDef")
    public ResponseEntity<List<Defect>> getAllVehicle() {
        return new ResponseEntity<>(vehicleService.getAll(), HttpStatus.OK);
    }
}
