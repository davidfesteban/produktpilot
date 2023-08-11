package dev.misei.controller;

import dev.misei.application.UserProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.payload.UserPayload;
import dev.misei.repository.OrganizationRepository;
import dev.misei.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.misei.domain.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/api/private/user")
public class UserController extends BaseCrudController {

    private final UserProcessor userProcessor;

    public UserController(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, OrganizationRepository organizationRepository, UserProcessor userProcessor) {
        super(jwtTokenProvider, userRepository, organizationRepository);
        this.userProcessor = userProcessor;
    }

    @PostMapping("/create")
    public ResponseEntity<UserPayload> create(@RequestBody UserPayload userPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(userProcessor.create(INSTANCE.toEntity(userPayload), user)), tokenRequest);
    }

    @PostMapping("/modify")
    public ResponseEntity<UserPayload> modify(@RequestBody UserPayload userPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(userProcessor.modify(INSTANCE.toEntity(userPayload), user)), tokenRequest);
    }

    @GetMapping("/delete")
    public ResponseEntity<UserPayload> delete(String userName, @RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(userProcessor.delete(userName, user)), tokenRequest);
    }
}
