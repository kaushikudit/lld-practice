package com.example.lld.lldpractice.cabmatching.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class DriverRiderMapping {
    private String riderName;
    private int ratingToRider;
    private String driverName;
    private int ratingToDriver;
}
