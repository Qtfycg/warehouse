package com.qtfycg.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;


@TableName(value ="hotel")
@Data
public class hotel {
    private Long id;
    private String name;
    private String location;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private String avatar;
}