package com.example.consumer.aop;


import com.example.consumer.annotation.AccumulatedEventCalculation;
import com.example.consumer.aop.util.ObjectCasting;
import com.example.consumer.request.AddNewOrderChildS1Request;
import com.example.consumer.request.OrderRequest;
import com.example.consumer.request.ProductInCartRequest;
import com.example.consumer.aop.util.impl.ProductInCartRequestCasting;
import com.example.consumer.aop.util.impl.UserAgencyIdCasting;
import com.example.consumer.enums.AccumulatedEventAction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class EventAspect {

    private Logger logger = LoggerFactory.getLogger(EventAspect.class);

    @After("@annotation(com.example.consumer.annotation.AccumulatedEventCalculation) && args(.., @RequestBody body)")
    public void calculateAccumulatedEvent(JoinPoint joinPoint, final Object body){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AddNewOrderChildS1Request addNewOrderChildS1Request = (AddNewOrderChildS1Request) body;
        Method method = methodSignature.getMethod();
        AccumulatedEventCalculation accumulatedEventCalculation = method.getAnnotation(AccumulatedEventCalculation.class);
        if (accumulatedEventCalculation.action().equals(AccumulatedEventAction.VALID_EVENT)){

        }else{

        }
    }

    private final HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
