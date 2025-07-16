package com.qtfycg.admin.domain.Vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class createVo {
    /*共有*/
    private Long id;
    private Integer stock;
    private String price;






    /*产品主表*/
    private String name;
    private String description;
    private Integer type;
    private Integer status;

    /*飞机票表*/
    private String flights;
    private String start;
    private String endpoint;
    private LocalDate departuretime;
    private LocalDate arrivetime;

    /*酒店*/
    private String location;

    /*火车*/
    private String trips;
    private String pathway;

}
