package es.misei.everi.controller;


import es.misei.everi.config.jwt.JwtTokenProvider;
import es.misei.everi.domain.payload.ActionResult;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;

@AllArgsConstructor
public abstract class BaseCrudController {
    private JwtTokenProvider jwtTokenProvider;

    ResponseEntity<String> perform(Function<String, ActionResult> action, String tokenRequest) {
        var userEmail = jwtTokenProvider.getUserEmailFromToken(tokenRequest);

        if (userEmail != null && !userEmail.isEmpty()) {
            var result = action.apply(userEmail);
            return result.isResult() ? ResponseEntity.ok(result.getMessage()) : new ResponseEntity<>(result.getMessage(), HttpStatus.I_AM_A_TEAPOT);
        }

        return new ResponseEntity<>("Wrong token", HttpStatus.I_AM_A_TEAPOT);
    }

    String processEmail(String tokenRequest) {
        return jwtTokenProvider.getUserEmailFromToken(tokenRequest);
    }
}
