package com.qtfycg.common.mq;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DelayedMessageSender {
    @Resource
    RabbitTemplate rabbitTemplate;

    public void send(String exchange, String routingKey, Object message, long delayMs) {
        MessagePostProcessor processor = msg -> {
            msg.getMessageProperties().setDelay((int) delayMs);
            return msg;
        };

        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, message, processor);
            log.info("⏳ 延迟消息发送 [{}ms]：{}", delayMs, message);
        } catch (Exception e) {
            log.error("❌ 延迟消息发送失败", e);
        }
    }
}
