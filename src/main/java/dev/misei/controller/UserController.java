package dev.misei.controller;

import dev.misei.application.OrganizationProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.domain.payload.UserPayload;
import dev.misei.repository.OrganizationRepository;
import dev.misei.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/private/user")
public class UserController extends BaseCrudController {

    private final UserProcessor userProcessor;

    public UserController(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, OrganizationRepository organizationRepository) {
        super(jwtTokenProvider, userRepository, organizationRepository);
    }

    @PostMapping("/create")
    public ResponseEntity<UserPayload> create(@RequestBody UserPayload userPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> UserINSTANCE.toPayload(userProcessor.createUser(INSTANCE.toEntity(organizationPayload), user)), tokenRequest);
    }

    @GetMapping("/details")
    public ResponseEntity<OrganizationPayload> details(@RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(organizationProcessor.details(user)), tokenRequest);
    }
}
