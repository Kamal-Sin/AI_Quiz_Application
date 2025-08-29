package com.quizapp.repository.mongo;

import com.quizapp.models.mongo.QuestionMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMongoRepository extends MongoRepository<QuestionMongo, String> {

    List<QuestionMongo> findByQuizId(String quizId);

    void deleteByQuizId(String quizId);
}
