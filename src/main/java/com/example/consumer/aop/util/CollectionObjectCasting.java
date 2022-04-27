package com.example.consumer.aop.util;

import java.io.BufferedReader;
import java.util.Collection;

public abstract class CollectionObjectCasting<T> extends ParentCasting<T> {
    public CollectionObjectCasting(String key, BufferedReader reader) {
        super(key, reader);
    }

    protected abstract Collection<T> cast();
}
