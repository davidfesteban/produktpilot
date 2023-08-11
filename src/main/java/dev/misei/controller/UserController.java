package dev.misei.controller;

import dev.misei.application.OrganizationProcessor;
import dev.misei.application.UserProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.mapper.UserMapper;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.domain.payload.UserPayload;
import dev.misei.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.misei.domain.mapper.OrganizationMapper.INSTANCE;

@RestController
@RequestMapping("/api/private/user")
public class UserController extends BaseCrudController {
    private final UserProcessor userProcessor;

    public UserController(OrganizationProcessor organizationProcessor, JwtTokenProvider jwtTokenProvider, OrganizationRepository organizationRepository, UserProcessor userProcessor) {
        super(organizationProcessor, jwtTokenProvider, organizationRepository);
        this.userProcessor = userProcessor;
    }

    @PostMapping("/create")
    public ResponseEntity<OrganizationPayload> create(@RequestBody UserPayload userPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(userProcessor.create(UserMapper.INSTANCE.toEntity(userPayload), org, user)), tokenRequest);
    }

    @PostMapping("/modify")
    public ResponseEntity<OrganizationPayload> modify(@RequestBody UserPayload userPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(userProcessor.modify(UserMapper.INSTANCE.toEntity(userPayload), org, user)), tokenRequest);
    }

    @GetMapping("/delete")
    public ResponseEntity<OrganizationPayload> delete(String userName, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(userProcessor.delete(userName, org, user)), tokenRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<UserPayload> delete(@RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> UserMapper.INSTANCE.toPayload(user), tokenRequest);
    }
}
