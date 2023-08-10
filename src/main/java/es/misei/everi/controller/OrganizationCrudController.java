package es.misei.everi.controller;

import es.misei.everi.application.OrganizationProcessor;
import es.misei.everi.config.jwt.JwtTokenProvider;
import es.misei.everi.domain.payload.organization.OrganizationPayload;
import es.misei.everi.domain.payload.organization.SimpleOrganizationPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/everi")
public class OrganizationCrudController extends BaseCrudController {

    private final OrganizationProcessor organizationProcessor;

    public OrganizationCrudController(JwtTokenProvider jwtTokenProvider, OrganizationProcessor organizationProcessor) {
        super(jwtTokenProvider);
        this.organizationProcessor = organizationProcessor;
    }

    @GetMapping("/findOrganizationByCode")
    public ResponseEntity<OrganizationPayload> findOrganizationByCode(String code, @RequestHeader("Authorization") String tokenRequest) {
        return ResponseEntity.ok(organizationProcessor.findByCode(code, processEmail(tokenRequest)));
    }

    @PostMapping("/createOrganization")
    public ResponseEntity<String> createOrganization(@RequestBody SimpleOrganizationPayload organizationPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(userEmail -> organizationProcessor.createOrganization(organizationPayload, userEmail), tokenRequest);
    }
}
