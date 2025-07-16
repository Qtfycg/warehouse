package com.qtfycg.common.mq.Sender;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FanoutMessageSender {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String exchangeName, Object message) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, "", message);
            log.info("✅ 广播消息已发送至 [{}]：{}", exchangeName, message);
        } catch (Exception e) {
            log.error("❌ 广播消息发送失败", e);
        }
    }
}
