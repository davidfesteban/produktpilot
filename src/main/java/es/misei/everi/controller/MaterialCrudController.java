package es.misei.everi.controller;

import es.misei.everi.application.MaterialProcessor;
import es.misei.everi.config.jwt.JwtTokenProvider;
import es.misei.everi.domain.payload.material.MaterialPayload;
import es.misei.everi.domain.payload.material.SimpleMaterialPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/everi")
public class MaterialCrudController extends BaseCrudController {

    private MaterialProcessor materialProcessor;

    public MaterialCrudController(JwtTokenProvider jwtTokenProvider, MaterialProcessor materialProcessor) {
        super(jwtTokenProvider);
        this.materialProcessor = materialProcessor;
    }

    @GetMapping("/findAllMaterialByOrganization")
    public ResponseEntity<List<MaterialPayload>> findAllMaterialByOrganization(String organizationCode, @RequestHeader("Authorization") String tokenRequest) {
        return ResponseEntity.ok(materialProcessor.findAllByOrganization(organizationCode, processEmail(tokenRequest)));
    }

    @PostMapping("/addMaterial")
    public ResponseEntity<String> addMaterial(String organizationCode, @RequestBody SimpleMaterialPayload payload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(userEmail -> materialProcessor.addMaterial(organizationCode, userEmail, payload), tokenRequest);
    }

    @GetMapping("/removeMaterial")
    public ResponseEntity<String> removeMaterial(String organizationCode, String qrCode, @RequestHeader("Authorization") String tokenRequest) {
        return perform(userEmail -> materialProcessor.removeMaterial(organizationCode, userEmail, qrCode), tokenRequest);
    }
}
