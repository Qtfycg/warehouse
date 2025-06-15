package com.qtfycg.admin.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;


@Data
@TableName("product")
public class product {
    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String description;

    private Long type;

    private Integer status;

    private String imageList;
}