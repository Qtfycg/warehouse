package com.qtfycg.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product_hotel")
public class product_hotel {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableId("hotel_name")
    private String hotelName;
    @TableId("address")
    private String address;
    @TableId("stars")
    private Integer stars;
    @TableId("room_type")
    private String roomType;
    @TableId("check_in_time")
    private Long checkInTime;
    @TableId("check_out_time")
    private Long checkOutTime;
}
