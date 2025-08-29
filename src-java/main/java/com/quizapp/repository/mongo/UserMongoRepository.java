package com.quizapp.repository.mongo;

import com.quizapp.models.mongo.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMongoRepository extends MongoRepository<UserMongo, String> {

    Optional<UserMongo> findByEmail(String email);

    boolean existsByEmail(String email);
}
