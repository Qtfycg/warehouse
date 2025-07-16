package com.qtfycg.admin.domain.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class flight {
    private Long id;
    private String flights;
    private String start;
    private String endpoint;
    private String price;
    private LocalDate departuretime;
    private LocalDate arrivetime;
    private Integer stock;

}
