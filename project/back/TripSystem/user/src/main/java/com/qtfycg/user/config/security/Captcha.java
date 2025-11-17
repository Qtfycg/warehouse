package com.qtfycg.user.config.security;

import com.wf.captcha.SpecCaptcha;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Configuration
public class Captcha {
    // 将验证码图片直接写到 HTTP 响应（浏览器显示）
    public static void writeToResponse(HttpServletResponse resp) throws IOException {
        SpecCaptcha cap = new SpecCaptcha(140, 50, 4); // 尺寸和长度可自定义
        cap.setCharType(SpecCaptcha.TYPE_ONLY_NUMBER);
        String code = cap.text().toLowerCase(); // 验证码文本，通常保存到 Redis 等处
        resp.setContentType("image/png");
        cap.out(resp.getOutputStream()); // 将图片写出到响应流
    }

    public static class CaptchaResult {
        public final String code;
        public final String base64Image;
        public CaptchaResult(String code, String base64Image) {
            this.code = code;
            this.base64Image = base64Image;
        }
    }
    // 返回 Base64 编码的图片字符串，和验证码文本（便于前端显示并把文本存 Redis）
    public static CaptchaResult toBase64() throws IOException {
        SpecCaptcha cap = new SpecCaptcha(140, 50, 4);
        cap.setCharType(SpecCaptcha.TYPE_ONLY_NUMBER);
        String code = cap.text().toLowerCase();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        cap.out(baos);
        String base64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
        return new CaptchaResult(code, base64);
    }




}
