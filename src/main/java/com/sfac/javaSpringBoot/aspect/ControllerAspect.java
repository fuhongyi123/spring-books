package com.sfac.javaSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {
     private static final Logger LOGGER= LoggerFactory.getLogger(ControllerAspect.class);
    /**
     * 关联在方法上的切点
     * 第一个*代表返回类型不限
     * 第二个*代表module下所有子包
     * 第三个*代表所有类
     * 第四个*代表所有方法
     * (..) 代表参数不限
     * Order 代表优先级，数字越小优先级越高
     */
    @Pointcut("execution(public * com.sfac.javaSpringBoot.modules.*.conntroller.*.*(..))")
//    @Order(1)
    public void controllerPoincut() {

    }
    //全路径+方法名
    @Before(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPoincut()")
    public void beforeController(JoinPoint joinPoint){
       ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      HttpServletRequest request= attributes.getRequest();
      LOGGER.debug("请求来源："+request.getRemoteAddr());
      LOGGER.debug("请求URL:"+request.getRequestURL().toString());
      LOGGER.debug("请求方式："+request.getMethod());
      LOGGER.debug("相应方法："+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
      LOGGER.debug("请求参数："+ Arrays.toString(joinPoint.getArgs()));
      LOGGER.debug("===========beforeController");
    }
    @Around(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPoincut()")
    public Object arroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.sfac.javaSpringBoot.aspect.ControllerAspect.controllerPoincut()")
    public void afterController(){
        LOGGER.debug("===========afterController");
    }
}
