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
    private CacheTwitter<UserModel> userCache;


    //EndPoint to get all the users
    @GetMapping("/")
    public List<UserModel> getAll(){
        return serviceLayer.getAll();
    }

    //EndPoint to get all the followers of a user by userId
    @GetMapping("/users/getFollowers/{userId}")
    public List<String> getFollowers(@PathVariable String userId){
        UserModel user1 = userCache.retrieve(userId);
        if(user1 == null){
            user1 = this.getUser(userId);
            userCache.put(userId, user1);
        }
        List<String> followers = user1.getfollowers();
        return followers;
    }


    @GetMapping("/users/getBio/{userId}")
    public String getBio(@PathVariable String userId){
        UserModel user1 = userCache.retrieve(userId);
        if(user1 == null){
            user1 = this.getUser(userId);
            userCache.put(userId, user1);
        }
        String Bio = user1.getBio();
        return Bio;
    }

    //EndPoint to get the user from UserId
    @GetMapping("/users/{userId}")
    public UserModel getUser(@PathVariable String userId){
        UserModel FetchedFromCache = userCache.retrieve(userId);
        if(FetchedFromCache != null) {
            System.out.println("User record found in cache : " + FetchedFromCache.getName());
            return FetchedFromCache;
        }

        UserModel FetchedFromDB = serviceLayer.getUser(userId);
        if(FetchedFromDB != null){
            userCache.put(userId, FetchedFromDB);
        }
        return FetchedFromDB;
    }

    //Adding a User in DB
    @PostMapping("/users/save")
    public List<UserModel> storeUser(@RequestBody UserModel userModel){
        serviceLayer.storeUser(userModel);
        userCache.put(userModel.getName(), userModel);
        return serviceLayer.getAll();
    }


    //Common Users
    @GetMapping("/users")
    public List<String> getCommonFollowers(@RequestParam("user1") String username1, @RequestParam("user2") String username2){
        return serviceLayer.CommonFollowersfetch(username1, username2);
    }
}
