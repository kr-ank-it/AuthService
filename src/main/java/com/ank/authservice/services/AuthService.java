package com.ank.authservice.services;

import com.ank.authservice.dtos.SendEmailDto;
import com.ank.authservice.exceptions.InvalidTokenException;
import com.ank.authservice.exceptions.PasswordMismatchException;
import com.ank.authservice.exceptions.UserAlreadyExistsException;
import com.ank.authservice.exceptions.UserNotFoundException;
import com.ank.authservice.models.Token;
import com.ank.authservice.models.User;
import com.ank.authservice.repositories.TokenRepo;
import com.ank.authservice.repositories.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepo tokenRepo;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthService(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepo tokenRepo) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepo = tokenRepo;
    }

    public User signup(String email, String password) throws JsonProcessingException {
        Optional<User> userOptional = userRepo.findByEmailEquals(email);
        if(userOptional.isPresent()) {
            throw new UserAlreadyExistsException("User already exists. Please login");

        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        user = userRepo.save(user);
        // Send a message to Kafka topic for user signup
        SendEmailDto sendEmailDto = new SendEmailDto();
        sendEmailDto.setEmail(email);
        sendEmailDto.setSubject("Welcome to Ankit's Auth Service");
        sendEmailDto.setMessage("Thank you for signing up! Your account has been created successfully.");

        kafkaTemplate.send(
                "sendWelcomeEmailEvent",
                objectMapper.writeValueAsString(sendEmailDto));

        return user;


    }

    public Token login(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmailEquals(email);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User doesn't exist. Please sign up");
        }
        String storedPassword = userOptional.get().getPassword();
        if(!bCryptPasswordEncoder.matches(password,storedPassword)) {
            throw new PasswordMismatchException("Password is incorrect. Please try again");
        }

        Token token = new Token();
        token.setUser(userOptional.get());
        token.setValue(RandomStringUtils.randomAlphanumeric(64));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 60);
        Date dateAfter30Days = calendar.getTime();
        token.setExpiresAt(dateAfter30Days);

        return tokenRepo.save(token);

        // return new Pair<>(userOptional.get(), "Tokentobeadded");

    }

    @Override
    public User validateToken(String tokenValue) {
    Optional<Token> tokenOptional = tokenRepo.findByValueAndExpiresAtAfter(tokenValue, new Date());
    if(tokenOptional.isEmpty()) {
        throw new InvalidTokenException("Token mismatch. Please login again");
    }
        return tokenOptional.get().getUser();
    }
}
