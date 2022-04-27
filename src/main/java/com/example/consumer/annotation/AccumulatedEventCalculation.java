package com.example.consumer.annotation;

import com.example.consumer.enums.AccumulatedEventAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccumulatedEventCalculation {
    AccumulatedEventAction action() default AccumulatedEventAction.VALID_EVENT;
}
