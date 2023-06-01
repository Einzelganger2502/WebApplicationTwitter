package com.WebApplicationTwitter.CacheManage;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;


//generic function where the value can be of any datatype


public class CacheTwitter<T> {
    //String Key and Any generic value the cache can store
    private Cache<String, T> cacheObj;

    //Constructor to build Cache Store
    public CacheTwitter(TimeUnit time, int expiry){
        cacheObj = CacheBuilder.newBuilder().expireAfterWrite(expiry, time).concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public void put(String key, T value) {
        if(key != null && value != null) {
            cacheObj.put(key, value);
            // System.out.println("Record stored in " + value.getClass().getSimpleName() + " Cache with Key = " + key);
        }
    }
    public T retrieve(String key) {
        return cacheObj.getIfPresent(key);
    }

}
