package com.kenet.springbootstarter.interceptor;

import com.kenet.springbootstarter.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class MyInterceptor {

    @Pointcut("within (com.kenet.springbootstarter.controller..*) && !within(com.kenet.springbootstarter.controller.admin.LoginController)")
    public void pointCut() {
    }
    @Around("pointCut()")
    public Object trackInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            attributes.getResponse().sendRedirect("/login"); //手动转发到/login映射路径
        }
        return joinPoint.proceed();
    }
}
