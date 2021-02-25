package com.example.lld.lldpractice.apicounttracker.service;

import com.example.lld.lldpractice.Exception.CustomException;
import com.example.lld.lldpractice.apicounttracker.models.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class UserDetailsService {

    private Map<Long, UserDetails> userDetailsStorage;

    public UserDetailsService() {
        userDetailsStorage = new HashMap<>();
    }

    public UserDetails saveUserDetails(UserDetails userDetails) {
        log.info("User details are {}", userDetails);
        userDetailsStorage.put(userDetails.getRollNumber(), userDetails);
        return userDetailsStorage.get(userDetails.getRollNumber());
    }

    public UserDetails getUserDetails(Long rollNumber) throws Exception {
        log.info("rollnumber {}", rollNumber);
        try {
            if(Objects.nonNull(userDetailsStorage.get(rollNumber))) {
                return userDetailsStorage.get(rollNumber);
            } else {
                throw new CustomException(HttpStatus.NOT_FOUND, "User details does not exist in the system.");
            }
        } catch (CustomException ex) {
            log.error("User details having roll number {} doesn't exist in the sytem.", rollNumber);
            throw ex;
        }
    }
}
