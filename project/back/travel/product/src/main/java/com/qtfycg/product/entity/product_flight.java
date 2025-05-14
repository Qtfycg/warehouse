package com.qtfycg.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product_flight")
public class product_flight {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableId("flight_no")
    private String flightNo;
    @TableId("departure_airport")
    private String departureAirport;
    @TableId("arrival_airport")
    private String arrivalAirport;
    @TableId("departure_time")
    private Long departureTime;
    @TableId("arrival_time")
    private Long arrivalTime;
}
