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

    private String hotelName;

    private String address;

    private Integer stars;

    private String roomType;

    private Long checkInTime;

    private Long checkOutTime;
}
