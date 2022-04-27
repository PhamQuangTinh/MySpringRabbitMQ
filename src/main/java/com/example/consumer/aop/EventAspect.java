package com.example.consumer.aop;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
public class EventAspect {


    @After("@annotation(com.exemple.consumer.annotation.AccumulatedEventCalculation)")
    public void calculateAccumulatedEvent(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        AccumulatedEventCalculation accumulatedEventCalculation = method.getAnnotation(AccumulatedEventCalculation.class);
        if (accumulatedEventCalculation.action().equals(AccumulatedEventAction.VALID_EVENT)){
            getValidAccumulatedEvent(joinPoint);
        }else{

        }
    }

    @Pointcut
    public void getValidAccumulatedEvent(JoinPoint joinPoint){
        HttpServletRequest request = this.getRequest();


    }

    private final HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    private void getRequest1t(HttpServletRequest request){
        try {
            String userIdKey = "user_agency_id";
            String productKey = "list_product";
            BufferedReader reader = request.getReader();
            int userId = 0;
            List<ProductInCartRequest> productInCartRequests = new ArrayList<>();
            while(reader.readLine() != null){
                if(userId == 0){
                    userId = new UserAgencyIdCasting(userIdKey, reader).cast();
                } if (productInCartRequests.isEmpty()){
                    productInCartRequests = new ProductInCartRequestCasting(productKey, reader).cast();
                }
                if (userId > 0 && !productInCartRequests.isEmpty()){

                }
            }
        }catch (Exception ex){
            LogMe.exception(ex);
        }
    }

}
