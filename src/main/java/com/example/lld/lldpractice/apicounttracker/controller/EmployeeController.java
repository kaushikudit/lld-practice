package com.example.lld.lldpractice.apicounttracker.controller;

import com.example.lld.lldpractice.apicounttracker.models.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
@RequestMapping("/emp")
public class EmployeeController {

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> saveUserDetails(@RequestBody UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(new HashMap<String, String>(){{
            put("status", "Success");
        }});
    }
}
