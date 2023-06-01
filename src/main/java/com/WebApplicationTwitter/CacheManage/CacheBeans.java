package com.WebApplicationTwitter.CacheManage;

import com.WebApplicationTwitter.models.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheBeans {
    @Bean
    public CacheTwitter<UserModel> twitterUserCache() {
        return new CacheTwitter<UserModel>(5, TimeUnit.MINUTES);
    }
}
