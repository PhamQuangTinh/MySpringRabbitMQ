package com.example.consumer.aop.util.impl;

import com.example.consumer.aop.util.ObjectCasting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;

public class UserAgencyIdCasting extends ObjectCasting<Integer> {

    private Logger logger = LoggerFactory.getLogger(UserAgencyIdCasting.class);

    public UserAgencyIdCasting(String key, BufferedReader reader) {
        super(key, reader);
    }

    @Override
    public Integer cast() {
        try {
            String line;
            while((line = reader.readLine()) != null){
                if (line.contains(key)){
                    return this.getValueParameter(line,key);
                }
            }
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return 0;
    }
}
