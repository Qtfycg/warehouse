package com.qtfycg.admin.domain.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class createProductVo {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private Long type;
    private Integer status;
    private String imageList;
}
