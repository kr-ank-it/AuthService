package com.ank.authservice.services;

import com.ank.authservice.exceptions.PasswordMismatchException;
import com.ank.authservice.exceptions.UserAlreadyExistsException;
import com.ank.authservice.exceptions.UserNotFoundException;
import com.ank.authservice.models.Token;
import com.ank.authservice.models.User;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Optional;

public interface IAuthService {
    public User signup(String email, String password);
    public Token login(String email, String password);
    public User validateToken(String tokenValue);
}
