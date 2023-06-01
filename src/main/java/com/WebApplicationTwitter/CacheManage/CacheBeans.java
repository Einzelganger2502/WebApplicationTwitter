package com.WebApplicationTwitter.CacheManage;

import com.WebApplicationTwitter.models.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheBeans {

    //Initialising Cache to store UserModel
    @Bean
    public CacheTwitter<UserModel> twitterUserCache() {
        return new CacheTwitter<UserModel>(TimeUnit.MINUTES,5);
    }
}
