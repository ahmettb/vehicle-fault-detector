package com.example.toyotaprojesi.Controller;

import com.example.toyotaprojesi.Model.VehicleDefect;
import com.example.toyotaprojesi.Model.VehicleDefectDto;
import com.example.toyotaprojesi.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("add")
    public ResponseEntity<VehicleDefectDto>vehicleSave(@RequestBody VehicleDefectDto vehicleDefectDto)

    {
        vehicleService.vehicleAdd(vehicleDefectDto);
        return new ResponseEntity<>(vehicleDefectDto, HttpStatus.CREATED);

    }}
