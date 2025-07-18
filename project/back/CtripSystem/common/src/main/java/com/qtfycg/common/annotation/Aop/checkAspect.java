package com.qtfycg.common.annotation.Aop;


import com.qtfycg.common.JWT.jwtUtils;
import com.qtfycg.common.annotation.context.loginHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

@Aspect
@Component
@Slf4j
public class checkAspect {

    @Resource
    private jwtUtils jwtUtils;

    @Pointcut("@annotation(com.qtfycg.common.annotation.Login)")
    public void loginPointcut() {}

    @Around("loginPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getCurrentHttpRequest();
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "无法获取请求信息");
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "请先登录");
        }

        String token = authHeader.replace("Bearer ", "");
        try {
            Long userId = jwtUtils.getUserId(token);
            if (userId == null) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token 无效或已过期");
            }

            loginHolder.setUserId(userId); // 设置 ThreadLocal
            return joinPoint.proceed();
        } catch (Exception e) {
            log.warn("Token 解析失败: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token 校验失败");
        } finally {
            loginHolder.clear(); // 清理 ThreadLocal
        }
    }

    private HttpServletRequest getCurrentHttpRequest() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }

}
