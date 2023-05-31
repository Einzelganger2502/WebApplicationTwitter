package com.WebApplicationTwitter.repositories;

import com.WebApplicationTwitter.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<UserModel, String> {
    UserModel findByname(String username);
}
