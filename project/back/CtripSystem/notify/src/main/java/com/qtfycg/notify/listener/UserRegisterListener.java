package com.qtfycg.notify.listener;

import com.qtfycg.common.mq.message.userRegistry;
import com.qtfycg.notify.service.emailService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRegisterListener {

    @Resource
    emailService emailService;

    @RabbitListener(queues = "user.register.notify")
    public void handleRaw(userRegistry msg) {
        log.info("✅ 收到 UserRegisterMessage：{}", msg);

        String subject = "欢迎注册携程系统";
        String content = String.format(
                "亲爱的 %s，感谢注册携程系统！请查阅系统使用说明，祝您愉快！",
                msg.getUsername()
        );

        emailService.sendSimpleMail(msg.getEmail(), subject, content);
    }
}
