package com.qtfycg.order.domain.Vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class createVo {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal amount;
    private Integer status;
    private String name;
    private String phone;
    private String address;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
