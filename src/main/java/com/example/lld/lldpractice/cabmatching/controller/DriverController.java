package com.example.lld.lldpractice.cabmatching.controller;

import com.example.lld.lldpractice.cabmatching.service.DriverRiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/cab-matching")
public class DriverController {

    @Autowired
    private DriverRiderService driverRiderService;

    @RequestMapping(value = "/{rider}", method = RequestMethod.GET)
    public ResponseEntity<?> findSuitableDriverForRider(@PathVariable String rider) {
        log.info("Got request for finding suitable driver for rider {}", rider);
        return driverRiderService.findSuitableDriverForRider(rider);
    }
}
