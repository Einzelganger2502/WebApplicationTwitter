package com.WebApplicationTwitter.services;


import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.json.JsonData;
import com.WebApplicationTwitter.CacheManage.CacheTwitter;
import com.WebApplicationTwitter.models.ElasticSearchModel;
import com.WebApplicationTwitter.models.UserModel;
import com.WebApplicationTwitter.repositories.ElasticSearchRepo;
import com.WebApplicationTwitter.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceLayer {

    @Autowired
    public ElasticsearchOperations elasticsearchOperations;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CacheTwitter<UserModel> twitterUserCache;

    //getting a single user
    public UserModel getUser(String username){
        return userRepo.findByname(username);
    }

    //getting all followers
    public List<UserModel> getAll(){
        return userRepo.findAll();
    }

    //getting common followers
    public List<String> CommonFollowersfetch(String username1, String username2){
        UserModel user1 = twitterUserCache.retrieve(username1);
        UserModel user2 = twitterUserCache.retrieve(username2);
        if(user1 == null){
            user1 = this.getUser(username1);
            twitterUserCache.put(username1, user1);
        }
        if(user2 == null){
            user2 = this.getUser(username2);
            twitterUserCache.put(username2, user2);
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

    //Various search functionalities to search tweets

    //Searching tweets by userId
    public List<String> SearchByUserName(String userId){
        Query Match = MatchQuery.of(m -> m.field("userId").query(userId))._toQuery();

        NativeQuery nativeQuery = NativeQuery.builder()
                .withSourceFilter(new FetchSourceFilterBuilder().withIncludes().build())
                .withQuery(Match)
                .withSort(Sort.by(Sort.Direction.ASC, "createdTime"))
                .build();

        List<String> results = new ArrayList<>();
        SearchHits<ElasticSearchModel> searchHits = elasticsearchOperations.search(nativeQuery, ElasticSearchModel.class);
        searchHits.forEach(searchHit ->  results.add(searchHit.getContent().getMessage()));
        return results;
    }
    //Searching Tweets by Keywords
    public List<String> SearchByKeyword(String query){
        Query multimatchquery = MatchQuery.of(m -> m.field("message").field("hashTag").query(query))._toQuery();

        NativeQuery nativeQuery = NativeQuery.builder()
                .withSourceFilter(new FetchSourceFilterBuilder().withIncludes().build())
                .withQuery(multimatchquery)
                .withSort(Sort.by(Sort.Direction.ASC, "createdTime"))
                .build();

        List<String> results = new ArrayList<>();
        SearchHits<ElasticSearchModel> searchHits = elasticsearchOperations.search(nativeQuery, ElasticSearchModel.class);
        searchHits.forEach(searchHit ->  results.add(searchHit.getContent().getMessage()));
        return results;
    }

    //Search Tweets within s given time range having a particular keyword
    public List<String> SearchByTime(Date StartDate, Date EndDate, String keyword){
        Query matchPhraseQuery = MatchPhraseQuery.of(m -> m.field("message").query(keyword))._toQuery();
        Query rangeQuery = RangeQuery.of(r -> r.field("createdTime")
                .gte((JsonData) StartDate)
                .lte((JsonData) EndDate))._toQuery();

        Query boolQuery = BoolQuery.of(b -> b
                .must(matchPhraseQuery)
                .filter(rangeQuery))._toQuery();

        NativeQuery nativeQuery = NativeQuery.builder()
                .withSourceFilter(new FetchSourceFilterBuilder().withIncludes().build())
                .withQuery(boolQuery)
                .withSort(Sort.by(Sort.Direction.ASC, "createdTime"))
                .build();

        List<String> results = new ArrayList<>();
        SearchHits<ElasticSearchModel> searchHits = elasticsearchOperations.search(nativeQuery, ElasticSearchModel.class);
        searchHits.forEach(searchHit ->  results.add(searchHit.getContent().getMessage()));
        return results;
    }
    public void storeUser(UserModel userModel){
        userRepo.save(userModel);
    }
}
