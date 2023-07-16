package com.example.toyotaprojesi.Service;


import com.example.toyotaprojesi.Model.*;
import com.example.toyotaprojesi.Repository.DefectRepository;
import com.example.toyotaprojesi.Repository.VehicleDefectLocationRepo;
import com.example.toyotaprojesi.Repository.VehicleDefectRepository;
import com.example.toyotaprojesi.Repository.VehicleRepository;
import com.example.toyotaprojesi.exception.VehicleAlreadyExistException;
import com.example.toyotaprojesi.exception.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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


    public void updateVehicle(long id, VehicleDefectDto vehicle) throws VehicleNotFoundException {

        Vehicle oldVehicle = vehicleRepository.activeVehicleById(id);
        if (oldVehicle != null) {
            oldVehicle.setMarka(vehicle.getMarka());
            oldVehicle.setModel(vehicle.getModel());
            oldVehicle.setYil(vehicle.getYil());
            oldVehicle.setRenk(vehicle.getRenk());
            vehicleRepository.save(oldVehicle);
        } else {
            throw new VehicleNotFoundException("Vehicle not found with id : " + id);
        }
    }

    public void deleteVehicle(long id) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.activeVehicleById(id);
        List<VehicleDefect> vehicleDefects = vehicleDefectRepository.findByVehicle(vehicle);
        if (vehicle != null) {
            vehicle.setDeleted(true);
            vehicleDefects.forEach(
                    vehicleDefect -> {
                        vehicleDefect.getDefect().setDeleted(true);
                        vehicleDefectRepository.save(vehicleDefect);
                    }
            );
            vehicleRepository.save(vehicle);
        } else {
            throw new VehicleNotFoundException("Vehicle not found with id : " + id);
        }
    }


    public List<VehicleDefectResponse> getVehicleInfo(long id) throws VehicleNotFoundException {

        List<VehicleDefectResponse> vehicleResponse = new ArrayList<>();

        Vehicle vehicleOptional = vehicleRepository.activeVehicleById(id);
        if (vehicleOptional == null) {
            throw new VehicleNotFoundException("Vehicle not found with id : " + id);
        }

        Vehicle vehicle = vehicleOptional;
        List<VehicleDefect> vehicleDefectList = vehicleDefectRepository.findByVehicle(vehicle);
        List<DefectLocation> defectLocations = new ArrayList<>();


        for (VehicleDefect vehicleDefect : vehicleDefectList) {
            List<DefectLocation> defectLocationListForDefect = vehicleDefectLocationRepo.findByDefect(vehicleDefect.getDefect());
            defectLocations.addAll(defectLocationListForDefect);
            for (DefectLocation defectLocation : defectLocationListForDefect) {
                VehicleDefectResponse vehicleDefectResponse = new VehicleDefectResponse();
                vehicleDefectResponse.setMarka(vehicle.getMarka());
                vehicleDefectResponse.setModel(vehicle.getModel());
                vehicleDefectResponse.setYil(vehicle.getYil());
                vehicleDefectResponse.setRenk(vehicle.getRenk());
                vehicleDefectResponse.setHataAdi(vehicleDefect.getDefect().getDefectName());
                vehicleDefectResponse.setXkoordinat(defectLocation.getxKoordinati());
                vehicleDefectResponse.setYkoordinat(defectLocation.getyKoordinati());
                vehicleResponse.add(vehicleDefectResponse);
            }
        }

        return vehicleResponse;

    }


    public List<Defect> getAll() {
        return defectRepository.activeDefects();

    }

    public void vehicleAdd(VehicleDefectDto vehicleDefectDto) throws VehicleAlreadyExistException {
        if (vehicleRepository.activeVehicleByModelNo(vehicleDefectDto.getModelNo()) != null) {
            throw new VehicleAlreadyExistException("Vehicle Already Exist");
        }


        Vehicle vehicle = new Vehicle();
        vehicle.setModel(vehicleDefectDto.getModel());
        vehicle.setMarka(vehicleDefectDto.getMarka());
        vehicle.setRenk(vehicleDefectDto.getRenk());
        vehicle.setModelNo(vehicleDefectDto.getModelNo());
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

    public void saveImage(byte[] imageByte, int x, int y) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
        BufferedImage image = ImageIO.read(bais);

        Graphics2D graphics2D = image.createGraphics();

        graphics2D.setColor(Color.RED);
        graphics2D.drawOval(x, y, 30, 30);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();


    }

    public VehicleDefectDto addDefectToVehicleByModelNo(String modelNo, VehicleDefectDto vehicleDefectDto, MultipartFile resim) throws VehicleNotFoundException, IOException, SQLException {


        Vehicle vehicle = vehicleRepository.findByModel(modelNo).orElseThrow(() -> new VehicleNotFoundException("Vehicle Not Found with Model No : " + modelNo));


        Defect defect = new Defect();
        defect.setDefectName(vehicleDefectDto.getHataAdi());

        defectRepository.save(defect);
        byte[]resimByte=resim.getBytes();

        ByteArrayInputStream bais = new ByteArrayInputStream(resimByte);
        BufferedImage image = ImageIO.read(bais);

        Graphics2D graphics2D = image.createGraphics();

        graphics2D.setColor(Color.RED);
        graphics2D.drawOval(vehicleDefectDto.getxKoordinati(), vehicleDefectDto.getyKoordinati(), 30, 30);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();
        Blob blob = new SerialBlob(bytes);


        DefectLocation defectLocation = new DefectLocation();
        defectLocation.setyKoordinati(vehicleDefectDto.getyKoordinati());
        defectLocation.setxKoordinati(vehicleDefectDto.getxKoordinati());
        defectLocation.setDefect(defect);

        vehicleDefectLocationRepo.save(defectLocation);


        VehicleDefect vehicleDefect = new VehicleDefect();
        vehicleDefect.setVehicle(vehicle);
        vehicleDefect.setDefect(defect);
        vehicleDefect.setResim(bytes);
        vehicleDefectRepository.save(vehicleDefect);
        vehicleDefectDto.setMarka(vehicle.getMarka());
        vehicleDefectDto.setModel(vehicle.getModel());
        vehicleDefectDto.setModelNo(vehicle.getModelNo());
        vehicleDefectDto.setYil(vehicle.getYil());
        vehicleDefectDto.setRenk(vehicle.getRenk());
        return vehicleDefectDto;

    }

    public byte[] resimGetir(long hataId) throws SQLException, IOException {

        VehicleDefect vehicleDefect = vehicleDefectRepository.findById(hataId).orElseThrow(null);

        if (vehicleDefect != null) {

           // byte[] bytes = vehicleDefect.getImage().getBytes(1, (int) vehicleDefect.getImage().length());

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(vehicleDefect.getResim());


            BufferedImage image = ImageIO.read(byteArrayInputStream);

            return vehicleDefect.getResim();
        }

        return null;
    }

}
