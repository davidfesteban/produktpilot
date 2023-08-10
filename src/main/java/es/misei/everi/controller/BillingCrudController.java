package es.misei.everi.controller;

import es.misei.everi.application.BillingProcessor;
import es.misei.everi.config.jwt.JwtTokenProvider;
import es.misei.everi.domain.payload.BillingPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/everi")
public class BillingCrudController extends BaseCrudController {

    private final BillingProcessor billingProcessor;

    public BillingCrudController(JwtTokenProvider jwtTokenProvider, BillingProcessor billingProcessor) {
        super(jwtTokenProvider);
        this.billingProcessor = billingProcessor;
    }

    @GetMapping("/findAllBillingByProject")
    public ResponseEntity<List<BillingPayload>> findAllBillingByProject(String projectCode, @RequestHeader("Authorization") String tokenRequest) {
        return ResponseEntity.ok(billingProcessor.findAllByProject(projectCode, processEmail(tokenRequest)));
    }

    @PostMapping("/addBilling")
    public ResponseEntity<String> addBilling(@RequestParam String projectCode, @RequestBody BillingPayload payload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(userEmail -> billingProcessor.addBilling(projectCode, userEmail, payload), tokenRequest);
    }

    @GetMapping("/removeBilling")
    public ResponseEntity<String> removeBilling(@RequestParam String projectCode, Long id, @RequestHeader("Authorization") String tokenRequest) {
        return perform(userEmail -> billingProcessor.removeBilling(projectCode, userEmail, id), tokenRequest);
    }
}
