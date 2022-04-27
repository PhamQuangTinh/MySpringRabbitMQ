package com.example.consumer.aop.util;

import com.ipi.framework.zsdk.log.LogMe;

import java.io.BufferedReader;

public abstract class ParentCasting<T> {
    protected String key;
    protected BufferedReader reader;

    public ParentCasting(String key, BufferedReader reader) {
        this.key = key;
        this.reader = reader;
    }

    protected   <T> T getValueParameter(String line, String key) {
        try {
            if (line.contains(key)) {
                line = line.trim().replace(key, "");
                return (T) line.replaceAll("[^a-zA-Z0-9]+", "");
            }
        }catch (Exception ex){
            LogMe.exception(ex);
        }
        return null;
    }
}
