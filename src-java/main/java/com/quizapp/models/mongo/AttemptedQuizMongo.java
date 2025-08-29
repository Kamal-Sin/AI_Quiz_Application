package com.quizapp.models.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "attempted_quizzes")
public class AttemptedQuizMongo {

    @Id
    private String id;
    private String userId;
    private String quizId;
    private String points;
    private String date;
    private String totalPoints;

    public AttemptedQuizMongo() {
    }

    public AttemptedQuizMongo(String userId, String quizId, String points, String date, String totalPoints) {
        this.userId = userId;
        this.quizId = quizId;
        this.points = points;
        this.date = date;
        this.totalPoints = totalPoints;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "AttemptedQuizMongo [id=" + id + ", userId=" + userId + ", quizId=" + quizId + ", points=" + points
                + ", date=" + date
                + ", totalPoints=" + totalPoints + "]";
    }
}
