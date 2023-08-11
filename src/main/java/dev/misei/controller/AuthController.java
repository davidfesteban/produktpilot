package dev.misei.controller;

import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.mapper.JoinToUserMapper;
import dev.misei.domain.payload.UserPayload;
import dev.misei.repository.OrganizationRepository;
import dev.misei.repository.TokenRepository;
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

    private final OrganizationRepository organizationRepository;
    private AuthenticationManager authenticationManager;
    private TokenRepository tokenRepository;
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
        var organization = organizationRepository.findAll().stream().findFirst();

        if (organizationRepository.count() != 0) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                    .header("Message", "Resource already exist").build();
        }

        var user = new JoinToUserMapper(passwordEncoder).apply(userPayload);

        try {
            organizationRepository.save(new Organization().addUser(user));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).header("Message", e.getMessage()).build();
        }
    }
}