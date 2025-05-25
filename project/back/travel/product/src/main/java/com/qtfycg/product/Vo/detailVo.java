package com.qtfycg.product.Vo;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class detailVo {
    private Long id;
    private String title;
    private String subTitle;
    private String destination;
    private String departure;
    private BigDecimal price;
    private Integer days;
    private String description;
    private String detailContent;
    private String coverUrl;
    private Integer type;

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
