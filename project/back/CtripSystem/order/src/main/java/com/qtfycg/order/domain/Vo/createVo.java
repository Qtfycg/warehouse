package com.qtfycg.order.domain.Vo;

import lombok.Data;

@Data
public class createVo {
    private Long userId; // 用户ID
    private Long productId; // 商品ID
    private String phone; // 联系电话
}
