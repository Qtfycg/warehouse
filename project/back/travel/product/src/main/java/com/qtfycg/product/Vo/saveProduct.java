package com.qtfycg.product.Vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class saveProduct {

        //产品标题
        private String title;
        //副标题
        private String subTitle;
        //目的地
        private String destination;
        //出发地
        private String departure;
        //封面图Url
        private String coverUrl;
        //优惠价格
        private BigDecimal price;
        //原价
        private BigDecimal originalPrice;
        //产品简介
        private String description;
        //详细介绍
        private String detailContent;
        //产品类型
        private Integer type;
        //上下架状态
        private Integer status;


        //三种产品相同的字段

        //主表ID
        private Long id;
        //出发时间
        private Long departureTime;
        //到达时间
        private Long arrivalTime;

        // 火车票

        //车次号
        private String trainNo;
        //出发车站
        private String departureStation;
        //到达车站
        private String arrivalStation;


        // 机票

        //航班号
        private String flightNo;
        //出发机场
        private String departureAirport;
        //到达机场
        private String arrivalAirport;

        // 酒店

        //酒店名称
        private String hotelName;
        //酒店地址
        private String address;
        //酒店星级
        private Integer stars;
        //房型描述
        private String roomType;
}
