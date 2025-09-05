package com.example.tripease.controller;

import com.example.tripease.Enum.Gender;
import com.example.tripease.dto.request.DriverRequest;
import com.example.tripease.dto.response.CustomerResponse;
import com.example.tripease.dto.response.DriverResponse;
import com.example.tripease.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    DriverService driverService;

    @PostMapping("/add")
    public DriverResponse addDriver(@RequestBody DriverRequest driverRequest) {
        return driverService.addDriver(driverRequest);
    }

    @GetMapping("/get-driver/{id}")
    public DriverResponse getDriverByID(@PathVariable("id") int driverId) {
        return driverService.getDriverByID(driverId);
    }

    @GetMapping("/get-all-driver")
    public List<DriverResponse> getAllDriver() {
        return driverService.getAllDriver();
    }
}
