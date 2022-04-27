package com.example.consumer.aop.util;

import java.io.BufferedReader;

public abstract class ObjectCasting<T> extends ParentCasting<T> {
    public ObjectCasting(String key, BufferedReader reader) {
        super(key, reader);
    }

    protected abstract T cast();
}
