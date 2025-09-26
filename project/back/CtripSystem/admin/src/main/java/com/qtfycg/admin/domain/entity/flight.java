package com.qtfycg.admin.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class flight {
    private Long id;
    private String flights;
    private String start;
    private String endpoint;
    private BigDecimal price;
    private LocalDate departuretime;
    private LocalDate arrivetime;
    private Integer stock;

}
