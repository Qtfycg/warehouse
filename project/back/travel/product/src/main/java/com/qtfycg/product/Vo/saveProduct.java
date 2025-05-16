package com.qtfycg.product.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class saveProduct {

        private String title;

        private String destination;

        private String departure; // 出发地（机票、火车票专属）


        private BigDecimal price;

        private Integer days;  // 行程天数或入住天数

        private String description;
        private String detailContent;
        private String coverUrl;

        private Integer type;  // 0-火车票，1-机票，2-酒店

        // 🔥 细分类专属字段（选填）

        // 火车票
        private String trainNo;
        private String departureStation;
        private String arrivalStation;
        private Long departureTime;
        private Long arrivalTime;

        // 机票
        private String flightNo;
        private String departureAirport;
        private String arrivalAirport;

        // 酒店
        private String hotelName;
        private String address;
        private Integer stars;
        private String roomType;

}
