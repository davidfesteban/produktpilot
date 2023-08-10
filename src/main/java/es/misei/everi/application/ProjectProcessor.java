package es.misei.everi.application;

import es.misei.everi.domain.entity.Organization;
import es.misei.everi.domain.entity.User;
import es.misei.everi.domain.mapper.project.ProjectMapper;
import es.misei.everi.domain.mapper.project.SimpleProjectMapper;
import es.misei.everi.domain.payload.ActionResult;
import es.misei.everi.domain.payload.project.ProjectPayload;
import es.misei.everi.domain.payload.project.SimpleProjectPayload;
import es.misei.everi.repository.AuthRepository;
import es.misei.everi.repository.OrganizationRepository;
import es.misei.everi.repository.ProjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectProcessor {

    private final SimpleProjectMapper simpleProjectMapper;
    private final ProjectMapper projectMapper;
    @PersistenceContext
    private EntityManager entityManager;
    private OrganizationRepository organizationRepository;
    private AuthRepository authRepository;
    private ProjectRepository projectRepository;

    @Transactional
    public List<ProjectPayload> findAllByOrganization(String organizationCode, String userEmail) {
        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var user = authRepository.findByEmail(userEmail).orElse(new User());
        var projects = projectRepository.findAllByOrganization(organization);

        if (organization.isAdminOrUser(user) && projects.isPresent()) {
            return projects.get().stream().map(projectMapper::toPayload).collect(Collectors.toList());
        }
        return List.of();
    }


    @Transactional
    public ActionResult addProject(String organizationCode, String userEmail, SimpleProjectPayload payload) {
        payload.enrichWith(organizationCode);

        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var admin = authRepository.findByEmail(userEmail).orElse(new User());
        var project = projectRepository.findByCode(payload.getCode());

        if (project.isEmpty() && organization.isAdmin(admin)) {
            organization.addProject(simpleProjectMapper.toEntity(payload));

            entityManager.merge(organization);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("Error adding project");
    }

    @Transactional
    public ActionResult removeProject(String organizationCode, String userEmail, String projectCode) {
        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var admin = authRepository.findByEmail(userEmail).orElse(new User());
        var project = projectRepository.findByCode(projectCode);

        if (project.isPresent() && organization.isAdmin(admin) && organization.hasProject(project.get())) {
            organization.removeProject(project.get());

            entityManager.merge(organization);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("Error removing project");
    }

}
