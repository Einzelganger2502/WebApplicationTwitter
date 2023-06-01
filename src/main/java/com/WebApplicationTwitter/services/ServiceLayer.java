package com.WebApplicationTwitter.services;


import com.WebApplicationTwitter.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.WebApplicationTwitter.CacheManage.CacheTwitter;
import com.WebApplicationTwitter.models.UserModel;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServiceLayer {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CacheTwitter<UserModel> twitterUserCache;

    //getting a single user
    public UserModel getUser(String username){
        return userRepo.findByname(username);
    }

    //getting all followers
    public List<UserModel> getAllUsers(){
        return userRepo.findAll();
    }

    //getting common followers
    public List<String> CommonFollowersfetch(String username1, String username2){
        UserModel user1 = twitterUserCache.get(username1);
        UserModel user2 = twitterUserCache.get(username2);
        if(user1 == null){
            user1 = this.getUser(username1);
            twitterUserCache.add(username1, user1);
        }
        if(user2 == null){
            user2 = this.getUser(username2);
            twitterUserCache.add(username2, user2);
        }

        List<String> commonFollowers = new ArrayList<String>();
        if(user1 == null || user2 == null) return commonFollowers;

        List<String>followers2 = user2.getfollowers();
        Set<String> commons = new HashSet<String>(user1.getfollowers());

        for(String follower : followers2){
            if(commons.contains(follower)){
                commonFollowers.add(follower);
            }
        }
        return commonFollowers;
    }

    public void storeUser(UserModel userModel){
        userRepo.save(userModel);
    }
}
