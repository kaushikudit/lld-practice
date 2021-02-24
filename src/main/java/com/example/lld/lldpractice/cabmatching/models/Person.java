package com.example.lld.lldpractice.cabmatching.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public abstract class Person {
    String name;
    String uuid;
    Map<String, Rating> ratingMap;

    public Person(String name) {
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
        this.ratingMap = new HashMap<>();
    }

    public void setRating(String uuid, Rating rating) {
        ratingMap.put(uuid, rating);
    }
}
