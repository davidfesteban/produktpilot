package es.misei.everi.application;

import es.misei.everi.domain.entity.Organization;
import es.misei.everi.domain.mapper.organization.OrganizationMapper;
import es.misei.everi.domain.mapper.organization.SimpleOrganizationMapper;
import es.misei.everi.domain.payload.ActionResult;
import es.misei.everi.domain.payload.organization.OrganizationPayload;
import es.misei.everi.domain.payload.organization.SimpleOrganizationPayload;
import es.misei.everi.repository.AuthRepository;
import es.misei.everi.repository.OrganizationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationProcessor {

    private final SimpleOrganizationMapper simpleOrganizationMapper;
    private final OrganizationMapper organizationMapper;
    @PersistenceContext
    private EntityManager entityManager;
    private OrganizationRepository organizationRepository;
    private AuthRepository authRepository;

    @Transactional
    public ActionResult createOrganization(SimpleOrganizationPayload organizationPayload, String userEmail) {
        var admin = authRepository.findByEmail(userEmail);
        if (!organizationRepository.existsByCode(organizationPayload.getCode()) && admin.isPresent()) {

            var organization = simpleOrganizationMapper.toEntity(organizationPayload);
            organization.addAdmin(admin.get());

            organizationRepository.saveAndFlush(organization);

            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("There is a chance that your code already exists");
    }

    @Transactional
    public OrganizationPayload findByCode(String code, String userEmail) {
        var organization = organizationRepository.findByCode(code).orElse(new Organization());
        var user = authRepository.findByEmail(userEmail);
        if (user.isPresent() && organization.isAdminOrUser(user.get())) {
            return organizationMapper.toPayload(organization);
        }

        return new OrganizationPayload();
    }
}
