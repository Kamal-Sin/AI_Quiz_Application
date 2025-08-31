package com.quizapp.services.mongo;

import com.quizapp.models.mongo.UserMongo;
import com.quizapp.repository.mongo.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserMongoService {

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registerUser(UserMongo user) {
        try {
            if (userMongoRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
            }

            // Encode password before saving
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserMongo savedUser = userMongoRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed");
        }
    }

    public ResponseEntity<?> loginUser(Map<String, String> login) {
        try {
            Optional<UserMongo> userOpt = userMongoRepository.findByEmail(login.get("email"));

            if (!userOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            UserMongo user = userOpt.get();
            if (!passwordEncoder.matches(login.get("password"), user.getPassword())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            // Create encoded user ID for session management
            String encodedUid = java.util.Base64.getEncoder().encodeToString(user.getId().getBytes());
            Map<String, String> data = new HashMap<>();
            data.put("id", encodedUid);
            data.put("username", user.getFirstName());

            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }

    public ResponseEntity<?> getUsers(String userId) {
        try {
            // Decode the user ID
            String decodedUserId = new String(java.util.Base64.getDecoder().decode(userId));
            Optional<UserMongo> userOpt = userMongoRepository.findById(decodedUserId);

            if (userOpt.isPresent()) {
                return ResponseEntity.ok(userOpt.get().getFirstName());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public boolean checkUser(String userId) {
        try {
            return userMongoRepository.findById(userId).isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
