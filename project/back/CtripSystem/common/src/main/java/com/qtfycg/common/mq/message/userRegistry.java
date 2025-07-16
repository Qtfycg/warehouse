package com.qtfycg.common.mq.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class userRegistry implements Serializable{
    private Long userId;
    private String username;
    private String email;
    private String phone;
}
