package com.sfac.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ServiceAspect {

    private static final Logger LOGGER= LoggerFactory.getLogger(ServiceAspect.class);
    @Pointcut("@annotation(com.sfac.javaSpringBoot.aspect.ServiceAnnotation)")
    public void servicePointCut(){

    }

    @Before(value = "com.sfac.javaSpringBoot.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();
        LOGGER.debug("请求来源："+request.getRemoteAddr());
        LOGGER.debug("请求URL:"+request.getRequestURL().toString());
        LOGGER.debug("请求方式："+request.getMethod());
        LOGGER.debug("相应方法："+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        LOGGER.debug("请求参数："+ Arrays.toString(joinPoint.getArgs()));
        LOGGER.debug("===========beforeService");
    }
    @Around(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPoincut()")
    public Object arroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPoincut()")
    public void afterService(){
        LOGGER.debug("===========afterService");
    }

}
