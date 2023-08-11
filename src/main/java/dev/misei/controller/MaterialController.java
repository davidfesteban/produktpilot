package dev.misei.controller;

import dev.misei.application.MaterialProcessor;
import dev.misei.application.OrganizationProcessor;
import dev.misei.config.jwt.JwtTokenProvider;
import dev.misei.domain.mapper.MaterialMapper;
import dev.misei.domain.mapper.WarehouseMapper;
import dev.misei.domain.payload.MaterialPayload;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.domain.payload.WarehousePayload;
import dev.misei.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static dev.misei.domain.mapper.OrganizationMapper.INSTANCE;


@RestController
@RequestMapping("/api/private/material")
public class MaterialController extends BaseCrudController {
    private final MaterialProcessor materialProcessor;

    public MaterialController(OrganizationProcessor organizationProcessor, JwtTokenProvider jwtTokenProvider, OrganizationRepository organizationRepository, MaterialProcessor materialProcessor) {
        super(organizationProcessor, jwtTokenProvider, organizationRepository);
        this.materialProcessor = materialProcessor;
    }


    @PostMapping("/create")
    public ResponseEntity<OrganizationPayload> create(String warehouseId, String standId, @RequestBody MaterialPayload materialPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(materialProcessor.create(warehouseId, standId, MaterialMapper.INSTANCE.toEntity(materialPayload), org, user)), tokenRequest);
    }

    @PostMapping("/modify")
    public ResponseEntity<OrganizationPayload> modify(String warehouseId, String standId, @RequestBody MaterialPayload materialPayload, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(materialProcessor.modify(warehouseId, standId, MaterialMapper.INSTANCE.toEntity(materialPayload), org, user)), tokenRequest);
    }

    @GetMapping("/delete")
    public ResponseEntity<OrganizationPayload> delete(String warehouseId, String standId, String materialId, @RequestHeader("Authorization") String tokenRequest) {
        return perform((org, user) -> INSTANCE.toPayload(materialProcessor.delete(warehouseId, standId, materialId, org, user)), tokenRequest);
    }
}
