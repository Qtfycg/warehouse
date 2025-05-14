package com.qtfycg.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("product_train")
public class product_train {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableId("train_no")
    private String trainNo;
    @TableId("departure_station")
    private String departureStation;
    @TableId("arrival_station")
    private String arrivalStation;
    @TableId("departure_time")
    private Long departureTime;
    @TableId("arrival_time")
    private Long arrivalTime;
}
