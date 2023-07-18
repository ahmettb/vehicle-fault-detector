package com.example.toyotaprojesi.Service;


import com.example.toyotaprojesi.Model.*;
import com.example.toyotaprojesi.Model.Image;
import com.example.toyotaprojesi.Repository.DefectRepository;
import com.example.toyotaprojesi.Repository.VehicleDefectLocationRepo;
import com.example.toyotaprojesi.Repository.VehicleDefectRepository;
import com.example.toyotaprojesi.Repository.VehicleRepository;
import com.example.toyotaprojesi.exception.ImageNotFoundException;
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
import java.util.*;
import java.util.List;

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


    public Map<String,Object> getVehicleInfo(long id) throws VehicleNotFoundException {


        Vehicle vehicle=vehicleRepository.activeVehicleById(id);
        Map <String, Object>vehicleInfo=new HashMap<>();
        vehicleInfo.put("marka",vehicle.getMarka());
        vehicleInfo.put("model",vehicle.getModel());
        vehicleInfo.put("modelNo",vehicle.getModelNo());
        vehicleInfo.put("yil",vehicle.getYil());
        vehicleInfo.put("renk",vehicle.getRenk());

      List<  VehicleDefect> vehicleDefect=vehicleDefectRepository.findByVehicle(vehicle);
        List<Map<String,Object>>defects=new ArrayList<>();

        vehicleDefect.forEach(vehicleDefect1 -> {
            List<DefectLocation>defectLocations= vehicleDefectLocationRepo.findByDefect(vehicleDefect1.getDefect());

            Map<String,Object>defectInfo=new HashMap<>();
            List<Map<String,Object>>locations=new ArrayList<>();


            defectLocations.forEach(defectLocation -> {
                Map<String,Object>location=new HashMap<>();

                location.put("Hata X Koordinati",defectLocation.getxKoordinati());
                location.put("Hata Y Koordinati",defectLocation.getyKoordinati());
                locations.add(location);

            });
            defectInfo.put("defect koordinatlar",locations);
            defectInfo.put("image",vehicleDefect1.getImage().toString());
            defectInfo.put("defectName",vehicleDefect1.getDefect().getDefectName());

            defects.add(defectInfo);

        });
        Map<String,Object>result=new HashMap<>();
        result.put("Vehicle",vehicleInfo);
        result.put("Defect",defects);
        return result;




//
//        List<VehicleDefectResponse> vehicleResponse = new ArrayList<>();
//
//        Vehicle vehicleOptional = vehicleRepository.activeVehicleById(id);
//        if (vehicleOptional == null) {
//            throw new VehicleNotFoundException("Vehicle not found with id : " + id);
//        }
//
//        Vehicle vehicle = vehicleOptional;
//        List<VehicleDefect> vehicleDefectList = vehicleDefectRepository.findByVehicle(vehicle);
//        List<DefectLocation> defectLocations = new ArrayList<>();
//
//
//        for (VehicleDefect vehicleDefect : vehicleDefectList) {
//            List<DefectLocation> defectLocationListForDefect = vehicleDefectLocationRepo.findByDefect(vehicleDefect.getDefect());
//            defectLocations.addAll(defectLocationListForDefect);
//            for (DefectLocation defectLocation : defectLocationListForDefect) {
//                VehicleDefectResponse vehicleDefectResponse = new VehicleDefectResponse();
//                vehicleDefectResponse.setMarka(vehicle.getMarka());
//                vehicleDefectResponse.setModel(vehicle.getModel());
//                vehicleDefectResponse.setYil(vehicle.getYil());
//                vehicleDefectResponse.setRenk(vehicle.getRenk());
//                vehicleDefectResponse.setHataAdi(vehicleDefect.getDefect().getDefectName());
//                vehicleDefectResponse.setXkoordinat(defectLocation.getxKoordinati());
//                vehicleDefectResponse.setYkoordinat(defectLocation.getyKoordinati());
//                vehicleResponse.add(vehicleDefectResponse);
//            }
//        }
//
//        return vehicleResponse;

    }


    public List<VehicleDefect> getAll() {

        return vehicleDefectRepository.findAll();

    }

    public void updateVehicleById(long id, VehicleDefectDto vehicleDefectDto) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.activeVehicleById(id);
        if (vehicle == null) throw new VehicleNotFoundException("Vehicle Not found");

        vehicle.setRenk(vehicleDefectDto.getRenk());
        vehicle.setYil(vehicleDefectDto.getYil());
        vehicle.setMarka(vehicleDefectDto.getMarka());
        vehicle.setModel(vehicleDefectDto.getModel());
        vehicle.setModelNo(vehicleDefectDto.getModelNo());
        vehicleRepository.save(vehicle);

    }


    public void vehicleAdd(VehicleDefectDto vehicleDefectDto, MultipartFile multipartFile) throws VehicleAlreadyExistException, IOException {
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

        Image image = new Image();
        image.setImage((saveImageByVehicle(multipartFile, vehicleDefectDto)));


        vehicleDefect.setVehicle(vehicle);
        vehicleDefect.setDefect(defect);
        if (multipartFile != null) vehicleDefect.setImage(image);
        vehicleDefectRepository.save(vehicleDefect);


    }

    //    public void saveImage(byte[] imageByte, int x, int y) throws IOException {
//
//        ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
//        BufferedImage image = ImageIO.read(bais);
//
//        Graphics2D graphics2D = image.createGraphics();
//
//        graphics2D.setColor(Color.RED);
//        graphics2D.drawOval(x, y, 30, 30);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", baos);
//        byte[] bytes = baos.toByteArray();
//
//
//    }
//
    private byte[] saveImageByVehicle(MultipartFile resim, VehicleDefectDto vehicleDefectDto) throws IOException {
        byte[] resimByte = resim.getBytes();

        ByteArrayInputStream bais = new ByteArrayInputStream(resimByte);
        BufferedImage image = ImageIO.read(bais);

        Graphics2D graphics2D = image.createGraphics();

        graphics2D.setColor(Color.RED);
        graphics2D.drawOval(vehicleDefectDto.getxKoordinati(), vehicleDefectDto.getyKoordinati(), 30, 30);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }

    public VehicleDefectDto addDefectToVehicleByModelNo(String modelNo, VehicleDefectDto vehicleDefectDto, MultipartFile resim) throws VehicleNotFoundException, IOException, SQLException, ImageNotFoundException {


        Vehicle vehicle = vehicleRepository.findByModel(modelNo).orElseThrow(() -> new VehicleNotFoundException("Vehicle Not Found with Model No : " + modelNo));

        if (resim.isEmpty()) throw new ImageNotFoundException("Image not found");

        Defect defect = new Defect();
        defect.setDefectName(vehicleDefectDto.getHataAdi());

        defectRepository.save(defect);


        DefectLocation defectLocation = new DefectLocation();
        defectLocation.setyKoordinati(vehicleDefectDto.getyKoordinati());
        defectLocation.setxKoordinati(vehicleDefectDto.getxKoordinati());
        defectLocation.setDefect(defect);

        vehicleDefectLocationRepo.save(defectLocation);


        VehicleDefect vehicleDefect = new VehicleDefect();
        Image image = new Image();
        vehicleDefect.setVehicle(vehicle);
        vehicleDefect.setDefect(defect);
        image.setImage(saveImageByVehicle(resim, vehicleDefectDto));
        vehicleDefect.setImage(image);
        vehicleDefectRepository.save(vehicleDefect);
        vehicleDefectDto.setMarka(vehicle.getMarka());
        vehicleDefectDto.setModel(vehicle.getModel());
        vehicleDefectDto.setModelNo(vehicle.getModelNo());
        vehicleDefectDto.setYil(vehicle.getYil());
        vehicleDefectDto.setRenk(vehicle.getRenk());
        return vehicleDefectDto;

    }

    public List<byte[]> getImagesByVehicleId(long id) throws IOException {


        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(null);
        List<byte[]> images = vehicleDefectRepository.getImgs(id);
        List<byte[]> pngImages = new ArrayList<>();


        for (byte[] image : images) {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ImageIO.write(bufferedImage, "png", baos);
            pngImages.add(baos.toByteArray());

        }

        return pngImages;


    }

    public byte[] resimGetir(long hataId) throws SQLException, IOException, ImageNotFoundException {

        VehicleDefect vehicleDefect = vehicleDefectRepository.findById(hataId).orElseThrow(() -> new ImageNotFoundException("Image not found"));


        return vehicleDefect.getImage().getImage();
    }

}
