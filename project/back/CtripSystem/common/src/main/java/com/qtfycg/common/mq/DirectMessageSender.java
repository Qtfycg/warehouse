package com.qtfycg.common.mq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DirectMessageSender {
    @Resource
    RabbitTemplate rabbitTemplate;

    public void send(String exchangeName, String routingKey, Object message) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
            log.info("✅ Direct 消息已发送至 [{} -> {}]：{}", exchangeName, routingKey, message);
        } catch (Exception e) {
            log.error("❌ Direct 消息发送失败", e);
        }
    }
}
