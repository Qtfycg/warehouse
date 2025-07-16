package com.qtfycg.notify.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 * - 声明交换机、队列、绑定
 * - 配置 RabbitTemplate 默认使用 JSON 消息转换器
 */
@Configuration
public class RabbitMQConfig {

    public static final String USER_REGISTER_EXCHANGE = "user.register.exchange";
    public static final String USER_REGISTER_NOTIFY_QUEUE = "user.register.notify";

    // === 队列、交换机、绑定 ===

    @Bean
    public FanoutExchange userRegisterExchange() {
        return new FanoutExchange(USER_REGISTER_EXCHANGE, true, false);
    }

    @Bean
    public Queue userRegisterNotifyQueue() {
        return new Queue(USER_REGISTER_NOTIFY_QUEUE, true);
    }

    @Bean
    public Binding bindUserRegisterNotify() {
        return new Binding(USER_REGISTER_NOTIFY_QUEUE,
                Binding.DestinationType.QUEUE,
                USER_REGISTER_EXCHANGE,
                "",
                null);
    }

    // === 消费端 JSON 转换器 ===

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter(new ObjectMapper());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }



    }

