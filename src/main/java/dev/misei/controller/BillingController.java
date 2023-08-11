package dev.misei.controller;

import dev.misei.application.BillingProcessor;
import dev.misei.application.OrganizationProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.mapper.BillingMapper;
import dev.misei.domain.mapper.WarehouseMapper;
import dev.misei.domain.payload.BillingPayload;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.domain.payload.WarehousePayload;
import dev.misei.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.misei.domain.mapper.OrganizationMapper.INSTANCE;


@RestController
@RequestMapping("/api/private/billing")
public class BillingController extends BaseCrudController {

    private final BillingProcessor billingProcessor;

    public BillingController(OrganizationProcessor organizationProcessor, JwtTokenProvider jwtTokenProvider, OrganizationRepository organizationRepository, BillingProcessor billingProcessor) {
        super(organizationProcessor, jwtTokenProvider, organizationRepository);
        this.billingProcessor = billingProcessor;
    }

    @PostMapping("/create")
    public ResponseEntity<OrganizationPayload> create(@RequestBody BillingPayload billingPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(billingProcessor.create(BillingMapper.INSTANCE.toEntity(billingPayload), org, user)), tokenRequest);
    }

    @PostMapping("/modify")
    public ResponseEntity<OrganizationPayload> modify(@RequestBody BillingPayload billingPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(billingProcessor.modify(BillingMapper.INSTANCE.toEntity(billingPayload), org, user)), tokenRequest);
    }

    @GetMapping("/delete")
    public ResponseEntity<OrganizationPayload> delete(long id, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(billingProcessor.delete(id, org, user)), tokenRequest);
    }
}
