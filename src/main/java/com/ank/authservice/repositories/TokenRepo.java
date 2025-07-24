package com.ank.authservice.repositories;

import com.ank.authservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    @Override
    Token save(Token token);


    Optional<Token> findByValueAndExpiresAtAfter(String tokenValue, Date expiresAt);
}
