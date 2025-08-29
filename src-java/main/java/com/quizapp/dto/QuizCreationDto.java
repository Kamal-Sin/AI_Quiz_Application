package com.quizapp.dto;

import java.util.List;

import com.quizapp.models.mongo.QuestionMongo;
import com.quizapp.models.mongo.QuizMongo;

public class QuizCreationDto {
	private QuizMongo quiz;
	private List<QuestionMongo> questions;

	public QuizMongo getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizMongo quiz) {
		this.quiz = quiz;
	}

	public List<QuestionMongo> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionMongo> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "QuizCreationDto [quiz=" + quiz + ", questions=" + questions + "]";
	}

}
