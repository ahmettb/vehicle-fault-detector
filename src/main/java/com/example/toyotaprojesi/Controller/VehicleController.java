package com.example.toyotaprojesi.Controller;

import com.example.toyotaprojesi.Model.*;
import com.example.toyotaprojesi.Service.VehicleService;
import com.example.toyotaprojesi.exception.ImageNotFoundException;
import com.example.toyotaprojesi.exception.VehicleNotFoundException;
import jakarta.annotation.Resource;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping("add")
    public ResponseEntity<VehicleDefectDto> vehicleAndDefectSave(@RequestPart("vehicle") VehicleDefectDto vehicleDefectDto, @RequestPart(required = false) MultipartFile multipartFile) throws Exception {
        vehicleService.vehicleAdd(vehicleDefectDto,multipartFile);
        return new ResponseEntity<>(vehicleDefectDto, HttpStatus.CREATED);
    }

    @PostMapping("addDefect/{modelNo}")
    public ResponseEntity<VehicleDefectDto> addDefectByModelNo(@PathVariable("modelNo") String modelNo, @RequestPart("data") VehicleDefectDto vehicleDefectDto, @RequestPart("resim") MultipartFile resim) throws VehicleNotFoundException, SQLException, IOException, ImageNotFoundException {
        return new ResponseEntity<>(vehicleService.addDefectToVehicleByModelNo(modelNo, vehicleDefectDto, resim), HttpStatus.OK);
    }

    @GetMapping("getimage/{id}")
    public ResponseEntity<byte[]> getResim(@PathVariable("id") long id) throws SQLException, IOException, ImageNotFoundException {


        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(vehicleService.resimGetir(id));

    }
    @GetMapping(value = "getImageById/{id}")
    public ResponseEntity <List<byte[]>>getImageByVehicleId(@PathVariable("id") long id) throws SQLException, IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(vehicleService.getImagesByVehicleId(id),headers,HttpStatus.OK);

    }


        @GetMapping("getVehicleInfo/{id}")
    public ResponseEntity<Map<String, Object>> getVehicleById(@PathVariable("id") long id) throws VehicleNotFoundException {



        return new ResponseEntity<>(vehicleService.getVehicleInfo(id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") long id) throws VehicleNotFoundException {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>("Delteed", HttpStatus.OK);
    }

    @GetMapping("getallVehDef")
    public ResponseEntity<List<VehicleDefect>> getAllVehicle() {
        return new ResponseEntity<>(vehicleService.getAll(), HttpStatus.OK);
    }


}

