package com.qtfycg.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("product")
public class product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String subTitle;
    private String destination;
    private String departure;
    private String coverUrl;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private String description;
    private String detailContent;
    private Integer type;
    private Integer status;

}
