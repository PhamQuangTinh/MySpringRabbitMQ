package com.example.consumer.aop.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;

public abstract class ParentCasting<T> {
    protected final String key;
    protected final BufferedReader reader;
    protected final ObjectMapper objectMapper = new ObjectMapper();
    public ParentCasting(String key, BufferedReader reader) {
        this.key = key;
        this.reader = reader;
    }

    protected <T> T getValueParameter(String line, String key) {
        try {
            if (line.contains(key)) {
                line = line.trim().replace(key, "");
                return (T) line.replaceAll("[^a-zA-Z0-9]+", "");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
