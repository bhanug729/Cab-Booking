package com.example.tripease.service;

import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.exception.DriverNotFoundException;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;

    public DriverResponse addDriver(DriverRequest driverRequest) {
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest); // Request DTO to Entity
        Driver savedDriver = driverRepository.save(driver);
        return  DriverTransformer.driverToDriverResponse(savedDriver);          // Entity to Response DTO
    }

    public DriverResponse getDriverByID(int driverId) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if(driver.isEmpty()) {
            throw new DriverNotFoundException("Driver Not Found!!");
        }
        return DriverTransformer.driverToDriverResponse(driver.get());
    }

    public List<DriverResponse> getAllDriver() {
        List<Driver> drivers = driverRepository.findAll();

        List<DriverResponse> driverResponses = new ArrayList<>();
        for(Driver driver:drivers) {
            driverResponses.add(DriverTransformer.driverToDriverResponse(driver));
        }
        return driverResponses;
    }
}
