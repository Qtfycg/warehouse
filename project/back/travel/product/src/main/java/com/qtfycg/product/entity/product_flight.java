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
    private String flightNo;
    private String departureAirport;
    private String arrivalAirport;
    private Long departureTime;
    private Long arrivalTime;
}
