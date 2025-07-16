package com.qtfycg.admin.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName(value ="hotel")
@Data
public class hotel {
    private Long id;

    private String location;

    private String price;

    private Integer stock;

    private String description;
}