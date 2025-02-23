package com.qtfycg.back.tool;

import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.stereotype.Service;

@Service
public class captcha {
    public ArithmeticCaptcha getArithmeticCaptcha() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        captcha.setLen(2);
        captcha.getArithmeticString();
        captcha.text();
        return captcha;
    }
}

