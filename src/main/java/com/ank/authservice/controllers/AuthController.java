package com.ank.authservice.controllers;

import com.ank.authservice.dtos.LoginRequestDto;
import com.ank.authservice.dtos.SignUpRequestDto;
import com.ank.authservice.dtos.UserDto;
import com.ank.authservice.models.Token;
import com.ank.authservice.models.User;
import com.ank.authservice.services.IAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }
    // signup
    @PostMapping("/signup")
    private UserDto signup(@RequestBody SignUpRequestDto signUpRequestDto) throws JsonProcessingException {
        User user = authService.signup(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return UserDto.fromUser(user);
    }

    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        Token token = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        // UserDto userDto = UserDto.fromUser(pair.a);
        return ResponseEntity.ok(token.getValue());
    }

    @GetMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") String tokenValue) {
        System.out.println("Validating token: " + tokenValue);
        User user = authService.validateToken(tokenValue);
        return UserDto.fromUser(user);
    }

}
