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

    private String trainNo;

    private String departureStation;

    private String arrivalStation;

    private Long departureTime;

    private Long arrivalTime;
}
