package dev.misei.controller;

import dev.misei.application.OrganizationProcessor;
import dev.misei.application.StandProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.mapper.StandMapper;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.domain.payload.StandPayload;
import dev.misei.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.misei.domain.mapper.OrganizationMapper.INSTANCE;


@RestController
@RequestMapping("/api/private/stand")
public class StandController extends BaseCrudController {

    private final StandProcessor standProcessor;

    public StandController(OrganizationProcessor organizationProcessor, JwtTokenProvider jwtTokenProvider, OrganizationRepository organizationRepository, StandProcessor standProcessor) {
        super(organizationProcessor, jwtTokenProvider, organizationRepository);
        this.standProcessor = standProcessor;
    }

    @PostMapping("/create")
    public ResponseEntity<OrganizationPayload> create(String warehouseId, @RequestBody StandPayload standPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(standProcessor.create(warehouseId, StandMapper.INSTANCE.toEntity(standPayload), org, user)), tokenRequest);
    }

    @PostMapping("/modify")
    public ResponseEntity<OrganizationPayload> modify(String warehouseId, @RequestBody StandPayload standPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(standProcessor.modify(warehouseId, StandMapper.INSTANCE.toEntity(standPayload), org, user)), tokenRequest);
    }

    @GetMapping("/delete")
    public ResponseEntity<OrganizationPayload> delete(String warehouseId, String standId, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(standProcessor.delete(warehouseId, standId, org, user)), tokenRequest);
    }
}
