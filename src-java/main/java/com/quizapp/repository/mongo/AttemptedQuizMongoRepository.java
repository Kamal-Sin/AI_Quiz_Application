package com.quizapp.repository.mongo;

import com.quizapp.models.mongo.AttemptedQuizMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttemptedQuizMongoRepository extends MongoRepository<AttemptedQuizMongo, String> {

    List<AttemptedQuizMongo> findByUserId(String userId);

    Optional<AttemptedQuizMongo> findByUserIdAndQuizId(String userId, String quizId);

    boolean existsByUserIdAndQuizId(String userId, String quizId);
}
