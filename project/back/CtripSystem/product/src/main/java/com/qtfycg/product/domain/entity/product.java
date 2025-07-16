package com.qtfycg.product.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("product")
public class product {
    private Long id;

    private String name;

    private Integer stock;

    private String description;

    private Long type;

    private Integer status;
}