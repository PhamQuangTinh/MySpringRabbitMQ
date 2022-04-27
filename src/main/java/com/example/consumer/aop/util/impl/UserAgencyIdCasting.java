package com.example.consumer.aop.util.impl;

import com.ipi.framework.zsdk.log.LogMe;
import com.vinhhoang.cmsservice.aop.util.ObjectCasting;

import java.io.BufferedReader;

public class UserAgencyIdCasting extends ObjectCasting<Integer> {

    public UserAgencyIdCasting(String key, BufferedReader reader) {
        super(key, reader);
    }

    @Override
    public Integer cast() {
        try {
            String line = reader.readLine();
            return this.getValueParameter(line,key);
        }catch (Exception ex){
            LogMe.exception(ex);
        }
        return 0;
    }
}
