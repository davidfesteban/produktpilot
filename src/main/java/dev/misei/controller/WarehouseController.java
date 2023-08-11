package dev.misei.controller;

import dev.misei.application.OrganizationProcessor;
import dev.misei.application.WarehouseProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.mapper.WarehouseMapper;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.domain.payload.WarehousePayload;
import dev.misei.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.misei.domain.mapper.OrganizationMapper.INSTANCE;


@RestController
@RequestMapping("/api/private/warehouse")
public class WarehouseController extends BaseCrudController {
    private final WarehouseProcessor warehouseProcessor;

    public WarehouseController(OrganizationProcessor organizationProcessor, JwtTokenProvider jwtTokenProvider, OrganizationRepository organizationRepository, WarehouseProcessor warehouseProcessor) {
        super(organizationProcessor, jwtTokenProvider, organizationRepository);
        this.warehouseProcessor = warehouseProcessor;
    }

    @PostMapping("/create")
    public ResponseEntity<OrganizationPayload> create(@RequestBody WarehousePayload warehousePayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(warehouseProcessor.create(WarehouseMapper.INSTANCE.toEntity(warehousePayload), org, user)), tokenRequest);
    }

    @PostMapping("/modify")
    public ResponseEntity<OrganizationPayload> modify(@RequestBody WarehousePayload warehousePayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(warehouseProcessor.modify(WarehouseMapper.INSTANCE.toEntity(warehousePayload), org, user)), tokenRequest);
    }

    @GetMapping("/delete")
    public ResponseEntity<OrganizationPayload> delete(String id, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(warehouseProcessor.delete(id, org, user)), tokenRequest);
    }
}
