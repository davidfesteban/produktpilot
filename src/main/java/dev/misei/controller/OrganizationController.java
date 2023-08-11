package dev.misei.controller;

import dev.misei.application.OrganizationProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.repository.OrganizationRepository;
import dev.misei.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.misei.domain.mapper.OrganizationMapper.INSTANCE;

@RestController
@RequestMapping("/api/private/organization")
public class OrganizationController extends BaseCrudController {

    private final OrganizationProcessor organizationProcessor;

    public OrganizationController(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, OrganizationRepository organizationRepository, OrganizationProcessor organizationProcessor) {
        super(jwtTokenProvider, userRepository, organizationRepository);
        this.organizationProcessor = organizationProcessor;
    }

    @PostMapping("/createFirstTime")
    public ResponseEntity<OrganizationPayload> createFirstTime(@RequestBody OrganizationPayload organizationPayload,
                                                               @RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(organizationProcessor.createFirstTimeOrganization(INSTANCE.toEntity(organizationPayload), user)), tokenRequest);
    }

    @GetMapping("/details")
    public ResponseEntity<OrganizationPayload> details(@RequestHeader("Authorization") String tokenRequest) {
        return perform(user -> INSTANCE.toPayload(organizationProcessor.details(user)), tokenRequest);
    }
}
