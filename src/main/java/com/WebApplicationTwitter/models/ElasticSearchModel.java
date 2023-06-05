package com.WebApplicationTwitter.models;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "index_for_tweets")
public class ElasticSearchModel {
    @Field(type = FieldType.Long)
    private long tweetId;

    @Field(type = FieldType.Text)
    private String message;

    @Field(type = FieldType.Date)
    private String createdTime;
    @Field(type = FieldType.Text)
    private List<String> hashTag;

    @Field(type = FieldType.Keyword)
    private String userId;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getHashTag() {
        return hashTag;
    }
    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }
    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHashTag(List<String> hashTag) {
        this.hashTag = hashTag;
    }


}
