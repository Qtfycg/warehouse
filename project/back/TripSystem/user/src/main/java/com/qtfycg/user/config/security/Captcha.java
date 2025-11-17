package com.qtfycg.user.config;

import com.wf.captcha.SpecCaptcha;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Captcha {
    public String getCaptcha(String phone) {
        SpecCaptcha captcha = new SpecCaptcha(130, 48,4);
        captcha.setCharType(com.wf.captcha.base.Captcha.TYPE_ONLY_NUMBER);
        return  captcha.text().toLowerCase();

    }

}
