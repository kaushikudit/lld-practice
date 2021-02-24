package com.example.lld.lldpractice.cabmatching.service;

import com.example.lld.lldpractice.cabmatching.models.DriverRiderMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DriverRiderService {

    @Autowired
    private InMemoryCacheService inMemoryCacheService;

    public ResponseEntity<?> findSuitableDriverForRider(String rider) {

        List<String> output = new ArrayList<>();

        List<DriverRiderMapping> driverRiderMappingList = inMemoryCacheService.getDriverRiderMappings();

        int ridesTakenByRider = 0, ratingGivenToRider = 0;
        for(DriverRiderMapping mapping : driverRiderMappingList) {
            if(mapping.getRiderName().equalsIgnoreCase(rider)) {
                ridesTakenByRider++;
                ratingGivenToRider += mapping.getRatingToRider();
            }
        }
        log.info("Sum of ratings {} and total number of rides taken by rider {}", ratingGivenToRider, ridesTakenByRider);
        Map<String, List<Integer>> driverTotalRatingWithNumberOfRides = new HashMap<>();
        Map<String, Boolean> isDriverEligible = new HashMap<>();
        for(DriverRiderMapping mapping : driverRiderMappingList) {
            List<Integer> ratingList = driverTotalRatingWithNumberOfRides.get(mapping.getDriverName());
            if(ratingList == null) {
                ratingList = new ArrayList<>();
            }
            if(ratingList.size() == 0) {
                ratingList.add(mapping.getRatingToDriver());
                ratingList.add(1);
            } else {
                ratingList.set(0, ratingList.get(0) + mapping.getRatingToDriver());
                ratingList.set(1, ratingList.get(1) + 1);
            }
            driverTotalRatingWithNumberOfRides.put(mapping.getDriverName(), ratingList);

            if(isDriverEligible.get(mapping.getDriverName()) == null) {
                isDriverEligible.put(mapping.getDriverName(), true);
            }
            if(mapping.getRiderName().equalsIgnoreCase(rider)) {
                if(mapping.getRatingToDriver() == 1 || mapping.getRatingToRider() == 1) {
                    isDriverEligible.put(mapping.getDriverName(), false);
                }
            }
        }
        log.info("Drivers total rating with total number of rides : {}", driverTotalRatingWithNumberOfRides);
        log.info("Status of all the drivers {}", isDriverEligible);

        double riderAvgRating = (double)ratingGivenToRider / ridesTakenByRider;
        log.info("Rider average rating {}", riderAvgRating);

        double max = Double.MIN_VALUE;
        String driverName = null;
        for(String key : driverTotalRatingWithNumberOfRides.keySet()) {
            if(isDriverEligible.get(key) == true) {
                List<Integer> ratingList = driverTotalRatingWithNumberOfRides.get(key);
                double driverAvgRating = (double) ratingList.get(0) / ratingList.get(1);
                log.info("Driver avg rating {}", driverAvgRating);
                if(driverAvgRating > max) {
                    max = driverAvgRating;
                    driverName = key;
                }
                if(driverAvgRating > riderAvgRating) {
                    output.add(key);
                }
            }
        }

        if(output.size() == 0) {
            output.add(driverName);
        }
        log.info("These {} are the suitable drivers for rider {}", output, rider);
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }
}
