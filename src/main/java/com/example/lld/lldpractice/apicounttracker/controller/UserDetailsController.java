package com.example.lld.lldpractice.apicounttracker.controller;

import com.example.lld.lldpractice.apicounttracker.models.UserDetails;
import com.example.lld.lldpractice.apicounttracker.service.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<?> saveUserDetails(@RequestBody UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsService.saveUserDetails(userDetails));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{rollNumber}")
    public ResponseEntity<?> fetchUserDetails(@PathVariable Long rollNumber) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsService.getUserDetails(rollNumber));
    }
}
