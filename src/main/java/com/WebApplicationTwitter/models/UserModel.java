package com.WebApplicationTwitter.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="twitterUser")
public class UserModel {
    @Indexed(unique = true)
    private String name;
    private String bio;
    private List<String> followers;

    public String getName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getfollowers() {
        return followers;
    }

//    public void setfollowers(List<String> followers) {
//        this.followers = followers;
//    }

    //Constructor parameterized
    public UserModel(String name, String bio, List<String> followers) {
        this.name = name;
        this.bio = bio;
        this.followers = followers;
    }
}
