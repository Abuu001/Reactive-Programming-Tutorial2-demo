package com.example.reactive_streams_demo.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Integer uuid;
    private String name;
    private BigDecimal price;
    private Double rating;
}
