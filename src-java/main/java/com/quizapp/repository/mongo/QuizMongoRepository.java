package com.quizapp.repository.mongo;

import com.quizapp.models.mongo.QuizMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizMongoRepository extends MongoRepository<QuizMongo, String> {

    Optional<QuizMongo> findByQuizId(String quizId);

    List<QuizMongo> findByUserIdOrderByDateDesc(String userId);
}
