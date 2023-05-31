package com.WebApplicationTwitter.controllers;

import com.WebApplicationTwitter.CacheManage.CacheTwitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.WebApplicationTwitter.models.UserModel;
import com.WebApplicationTwitter.services.ServiceLayer;

import java.util.List;

@RestController
@RequestMapping("/twitter")
public class ControllerTwitter {
    @Autowired
    private ServiceLayer serviceLayer;

    @Autowired
    private CacheTwitter<UserModel> twitterUserCache;

    @GetMapping("/")
    public List<UserModel> getAllUsers(){
        return serviceLayer.getAllUsers();
    }

//    @GetMapping("/users/getFollowers/{userId}")
//    public List<String> getFollowers(@PathVariable String userId){
//        UserModel userModel =
//    }


    @GetMapping("/users/{userId}")
    public UserModel getUser(@PathVariable String userId){
        UserModel cachedUserRecord = twitterUserCache.get(userId);
        if(cachedUserRecord != null) {
            System.out.println("User record found in cache : " + cachedUserRecord.getName());
            return cachedUserRecord;
        }

        UserModel userRecordFromDB = serviceLayer.getUser(userId);
        if(userRecordFromDB != null){
            twitterUserCache.add(userId, userRecordFromDB);
        }
        return userRecordFromDB;
    }
    @PostMapping("/users/save")
    public List<UserModel> storeUser(@RequestBody UserModel userModel){
        serviceLayer.storeUser(userModel);
        twitterUserCache.add(userModel.getName(), userModel);
        return serviceLayer.getAllUsers();
    }



    @GetMapping("/users")
    public List<String> getCommonFollowers(@RequestParam("user1") String username1, @RequestParam("user2") String username2){
        return serviceLayer.getCommonFollowers(username1, username2);
    }
}
