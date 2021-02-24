package com.example.lld.lldpractice.cabmatching.service;

import com.example.lld.lldpractice.cabmatching.models.DriverRiderMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class InMemoryCacheService {

    private static final List<DriverRiderMapping> DRIVER_RIDER_MAPPINGS = new ArrayList<>();

    @PostConstruct
    public void initMyCache() {
        DRIVER_RIDER_MAPPINGS.add(new DriverRiderMapping("ram", 3, "bheem", 5));
        DRIVER_RIDER_MAPPINGS.add(new DriverRiderMapping("laxman", 5, "nakul", 2));
        DRIVER_RIDER_MAPPINGS.add(new DriverRiderMapping("ram", 4, "sahedev", 2));
        DRIVER_RIDER_MAPPINGS.add(new DriverRiderMapping("bharat", 3, "bheem", 5));
        DRIVER_RIDER_MAPPINGS.add(new DriverRiderMapping("ram", 3, "bheem", 1));
        DRIVER_RIDER_MAPPINGS.add(new DriverRiderMapping("laxman", 5, "sahedev", 3));
        DRIVER_RIDER_MAPPINGS.add(new DriverRiderMapping("bharat", 5, "nakul", 5));
    }

    public List<DriverRiderMapping> getDriverRiderMappings() {
        return this.DRIVER_RIDER_MAPPINGS;
    }
}
