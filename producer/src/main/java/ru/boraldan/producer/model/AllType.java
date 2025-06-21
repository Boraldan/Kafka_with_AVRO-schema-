package ru.boraldan.producer.model;

import lombok.Data;


import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class AllType {
    private UUID id;
    private String name;
    private String email;
    private Integer age;
    private Boolean isActive;
    private Double balance;
    private Float rating;
    private Instant createdAt;
    private Map<String, String> attributes;
    private Map<String, Integer> scores;
    private List<String> comments;
    private Status status;


}
