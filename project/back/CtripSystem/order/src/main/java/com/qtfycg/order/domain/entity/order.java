package com.qtfycg.order.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value ="order")
@Data
public class order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal amount;
    private String status;
    private String name;
    private String phone;
    private String address;
    private String remark;
    private Date createTime;
    private Date updateTime;
}