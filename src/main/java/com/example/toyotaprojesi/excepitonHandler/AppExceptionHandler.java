package com.example.toyotaprojesi.excepitonHandler;

import com.example.toyotaprojesi.exception.VehicleAlreadyExistException;
import com.example.toyotaprojesi.exception.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(VehicleNotFoundException.class)
    public Map<String, String> handleVehicleNotFoundException(VehicleNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error Message", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(VehicleAlreadyExistException.class)
    public Map<String, String> handleVehicleAlreadyExist(VehicleAlreadyExistException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error Message", ex.getMessage());
        return errorMap;
    }



}
