package com.WebApplicationTwitter.CacheManage;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;


//generic function where the value can be of any datatype


public class CacheTwitter<T> {
    private Cache<String, T> cacheObj;

    //Constructor to build Cache Store
    public CacheTwitter(int expiryDuration, TimeUnit timeUnit) {
        cacheObj = CacheBuilder.newBuilder().expireAfterWrite(expiryDuration, timeUnit).concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public T get(String key) {
        return cacheObj.getIfPresent(key);
    }

    public void add(String key, T value) {
        if(key != null && value != null) {
            cacheObj.put(key, value);
           // System.out.println("Record stored in " + value.getClass().getSimpleName() + " Cache with Key = " + key);
        }
    }
}
