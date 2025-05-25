package com.qtfycg.product.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class searchVo {
    private String title;      // 搜索关键字
    private String type;     // 商品类型（如 hotel、flight 等）
    private Long page = 1L;  // 当前页码，默认第1页
    private Long limit = 20L;// 每页条数，默认10条
    private BigDecimal minPrice;     // 最小价格
    private BigDecimal maxPrice;     // 最大价格
}
