package dev.misei.controller;

import dev.misei.application.OrganizationProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.misei.domain.mapper.OrganizationMapper.INSTANCE;

@RestController
@RequestMapping("/api/private/organization")
public class OrganizationController extends BaseCrudController {

    public OrganizationController(JwtTokenProvider jwtTokenProvider, OrganizationRepository organizationRepository, OrganizationProcessor organizationProcessor) {
        super(organizationProcessor, jwtTokenProvider, organizationRepository);
    }

    @GetMapping("/modifyOrganizationNameAndLicense")
    public ResponseEntity<OrganizationPayload> modifyOrganizationNameAndLicense(String name, String license,
                                                                                @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(organizationProcessor.modifyOrganizationNameAndLicense(name, license, org, user)), tokenRequest);
    }

    @GetMapping("/details")
    public ResponseEntity<OrganizationPayload> details(@RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(org), tokenRequest);
    }
}
