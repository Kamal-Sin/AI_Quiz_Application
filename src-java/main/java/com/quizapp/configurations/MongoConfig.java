package com.quizapp.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("prod")
@EnableMongoRepositories(basePackages = "com.quizapp.repository.mongo")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "quizapp";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
