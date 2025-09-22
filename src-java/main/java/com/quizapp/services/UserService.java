package com.quizapp.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.quizapp.models.AttemptedQuiz;
import com.quizapp.models.Quiz;
import com.quizapp.models.User;
import com.quizapp.repository.AttemptedQuizRepository;
import com.quizapp.repository.QuizRepository;
import com.quizapp.repository.UserRepository;
import com.quizapp.utils.JwtUtil;

@Service
public class UserService {

	private UserRepository repo;
	private QuizRepository quizRepo;
	private QuestionService questionService;
	private AttemptedQuizRepository attemptedRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public UserService(UserRepository repo, QuizRepository quizRepo, QuestionService questionService,
			AttemptedQuizRepository attemptedRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.repo = repo;
		this.quizRepo = quizRepo;
		this.questionService = questionService;
		this.attemptedRepo = attemptedRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public ResponseEntity<?> registerUser(User user) {
		// Basic input validation
		if (user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payload");
		}

		String email = user.getEmail() == null ? "" : user.getEmail().trim().toLowerCase();
		String password = user.getPassword() == null ? "" : user.getPassword();

		if (!StringUtils.hasText(email) || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email");
		}

		if (!StringUtils.hasText(password) || password.length() < 8) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password must be at least 8 characters");
		}

		if (repo.findByEmail(email).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already registered");
		}

		String id = UUID.randomUUID().toString();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		repo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	public ResponseEntity<?> loginUser(Map<String, String> login) {
		String rawEmail = login.get("email");
		String rawPassword = login.get("password");

		if (!StringUtils.hasText(rawEmail) || !StringUtils.hasText(rawPassword)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email and password are required");
		}

		String email = rawEmail.trim().toLowerCase();
		Optional<User> res = repo.findByEmail(email);
		if (!res.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		User user = res.get();
		if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials");
		}

		byte[] userId = String.valueOf(user.getId()).getBytes();
		String encodedUid = Base64.getEncoder().encodeToString(userId);
		Map<String, String> data = new HashMap<>();
		data.put("id", encodedUid);
		data.put("username", user.getFirstName() != null ? user.getFirstName() : "");
		String jwt = jwtUtil.generateToken(user.getId());
		data.put("token", jwt);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

	public User getUser(String id) {
		User user = repo.findById(id).get();
		return user;
	}

	public boolean checkUser(String uid) {
		Optional<User> user = repo.findById(uid);
		if (!user.isPresent())
			return false;
		return true;
	}

	public ResponseEntity<List<Quiz>> allQuizzes(String userId) {
		Optional<List<Quiz>> all = quizRepo.findByUserIdOrderByDateDesc(userId);
		if (!all.isPresent())
			return ResponseEntity.ok().body(java.util.Collections.emptyList());
		return ResponseEntity.ok().body(all.get());
	}

	public ResponseEntity<Quiz> getQuiz(String quizId, String userId) {
		Optional<Quiz> res = quizRepo.findByQuizId(quizId);
		if (!res.isPresent())
			return ResponseEntity.notFound().build();
		if (userQuizRelation(userId, quizId)) {
			res.get().setUserId(null);
			return ResponseEntity.ok(res.get());
		}
		return ResponseEntity.notFound().build();
	}

	private boolean userQuizRelation(String userId, String quizId) {
		Optional<Quiz> res = quizRepo.findByQuizId(quizId);
		if (!res.isPresent() || res.get().getUserId() == null || res.get().getUserId().trim().isEmpty())
			return false;
		if (userId.equals(res.get().getUserId()))
			return true;
		return false;
	}

	public ResponseEntity<?> getQuestions(String quizId, String userId) {
		if (!userQuizRelation(userId, quizId))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return questionService.getQuiz(quizId);
	}

	public ResponseEntity<?> getAttempted(String userId) {
		List<AttemptedQuiz> attemptedQuizId = attemptedRepo.findByUserId(userId);
		if (attemptedQuizId.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		Map<String, List<?>> response = new HashMap<>();
		List<Map<String, String>> quizzes = new ArrayList<>();
		for (AttemptedQuiz quiz : attemptedQuizId) {
			Map<String, String> m = quizRepo.findByQuizIdNoDate(quiz.getQuizId());
			if (!m.isEmpty())
				quizzes.add(m);
		}

		response.put("attempted", attemptedQuizId);
		response.put("quizDetails", quizzes);
		return ResponseEntity.ok(response);

	}

	public ResponseEntity<?> getUsers(String userId) {
		Optional<String> res = repo.findByUserId(userId);
		if (!res.isPresent())
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return ResponseEntity.ok(res.get());
	}

}