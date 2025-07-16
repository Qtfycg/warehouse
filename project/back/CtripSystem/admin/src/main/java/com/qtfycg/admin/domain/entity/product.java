package com.qtfycg.admin.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("product")
public class product {
    private Long id;

    private String name;

    private Integer stock;

    private String description;

    private Integer type;

    private Integer status;

}