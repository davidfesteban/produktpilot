package es.misei.everi.controller;

import es.misei.everi.application.ProjectProcessor;
import es.misei.everi.config.jwt.JwtTokenProvider;
import es.misei.everi.domain.payload.project.ProjectPayload;
import es.misei.everi.domain.payload.project.SimpleProjectPayload;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/everi")
public class ProjectCrudController extends BaseCrudController {

    private final ProjectProcessor projectProcessor;

    public ProjectCrudController(JwtTokenProvider jwtTokenProvider, ProjectProcessor projectProcessor) {
        super(jwtTokenProvider);
        this.projectProcessor = projectProcessor;
    }

    @GetMapping("/findAllProjectByOrganization")
    public ResponseEntity<List<ProjectPayload>> findAllProjectByOrganization(String organizationCode, @RequestHeader("Authorization") String tokenRequest) {
        return ResponseEntity.ok(projectProcessor.findAllByOrganization(organizationCode, processEmail(tokenRequest)));
    }

    @PostMapping(path = "/addProject")
    public ResponseEntity<String> addProject(String organizationCode, @RequestBody SimpleProjectPayload payload, @RequestHeader("Authorization") String tokenRequest) {
        return perform(userEmail -> projectProcessor.addProject(organizationCode, userEmail, payload), tokenRequest);
    }

    @GetMapping("/removeProject")
    public ResponseEntity<String> removeProject(String organizationCode, String projectCode, @RequestHeader("Authorization") String tokenRequest) {
        return perform(userEmail -> projectProcessor.removeProject(organizationCode, userEmail, projectCode), tokenRequest);
    }
}
