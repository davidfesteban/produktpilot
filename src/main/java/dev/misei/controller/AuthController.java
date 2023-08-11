package dev.misei.controller;

import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.mapper.JoinToUserMapper;
import dev.misei.domain.payload.UserPayload;
import dev.misei.repository.TokenRepository;
import dev.misei.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private TokenRepository tokenRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider tokenProvider;

    @GetMapping("/login")
    public ResponseEntity<String> authenticateUser(String userName, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userName, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            var savedToken = tokenRepository.retrieveGeneratedToken(userName);

            if (savedToken.isEmpty() || !tokenProvider.validateToken(savedToken.get())) {
                String token = tokenProvider.generateToken(userName, password);
                tokenRepository.saveOverrideGeneratedToken(userName, token);
                return ResponseEntity.ok(token);
            }

            return ResponseEntity.ok(savedToken.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).header("Message", e.getMessage()).build();
        }
    }

    @PostMapping("/oneTimeJoin")
    public ResponseEntity<Void> oneTimeJoin(@RequestBody UserPayload userPayload) {
        if (userRepository.count() != 0) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                    .header("Message", "There is already one administrator").build();
        }

        try {
            userRepository.save(new JoinToUserMapper(passwordEncoder).apply(userPayload));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).header("Message", e.getMessage()).build();
        }
    }
}