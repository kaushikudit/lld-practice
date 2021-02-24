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
@ToString
@Builder
public class Driver extends Person{

    public Driver(String name) {
        super(name);

    }
}
