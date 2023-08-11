package dev.misei.controller;

import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.User;
import dev.misei.repository.OrganizationRepository;
import dev.misei.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

@AllArgsConstructor
public abstract class BaseCrudController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    //TODO: Check for license in each call
    private final OrganizationRepository organizationRepository;

    <T> ResponseEntity<T> perform(Function<User, T> action, String tokenRequest) {
        var userEmail = processEmail(tokenRequest);

        try {
            var user = userRepository.findByUserNameIgnoreCase(userEmail).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
            return ResponseEntity.ok(action.apply(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).header("Message", e.getMessage()).build();
        }
    }


    String processEmail(String tokenRequest) {
        return jwtTokenProvider.getUserEmailFromToken(tokenRequest);
    }

    public User processLicense(User user) {
        //TODO: Check for license in each call
        return user;
    }
}
