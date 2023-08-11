package dev.misei.controller;

import dev.misei.application.OrganizationProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.User;
import dev.misei.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.BiFunction;

@AllArgsConstructor
public abstract class BaseCrudController {

    final OrganizationProcessor organizationProcessor;
    private final JwtTokenProvider jwtTokenProvider;
    private final OrganizationRepository organizationRepository;

    //TODO: Maybe we need to add a listener and a lock for each call because of the way of managing organizations
    @Synchronized
    <T> ResponseEntity<T> perform(BiFunction<Organization, User, T> action, String tokenRequest) {
        var userEmail = processEmail(tokenRequest);

        try {

            var user = organizationRepository.findByUsers_UserNameIgnoreCaseProxy(userEmail).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
            var organization = organizationRepository.findAll().stream().findFirst().orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

            return ResponseEntity.ok(action.apply(organization, user));
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
