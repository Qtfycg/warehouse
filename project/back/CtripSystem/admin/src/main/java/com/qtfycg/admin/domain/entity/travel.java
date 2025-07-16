package com.qtfycg.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@TableName(value ="travel")
@Data
public class travel {
    private Long id;

    private String trips;

    private String start;

    private String endpoint;

    private String price;

    private LocalDate departuretime;

    private LocalDate arrivetime;

    private String pathway;

    private Integer stock;
}