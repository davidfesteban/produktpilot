package es.misei.everi.application;

import es.misei.everi.domain.entity.Organization;
import es.misei.everi.domain.entity.User;
import es.misei.everi.domain.mapper.material.MaterialMapper;
import es.misei.everi.domain.mapper.material.SimpleMaterialMapper;
import es.misei.everi.domain.payload.ActionResult;
import es.misei.everi.domain.payload.material.MaterialPayload;
import es.misei.everi.domain.payload.material.SimpleMaterialPayload;
import es.misei.everi.repository.AuthRepository;
import es.misei.everi.repository.MaterialRepository;
import es.misei.everi.repository.OrganizationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialProcessor {

    private final SimpleMaterialMapper simpleMaterialMapper;
    private final MaterialMapper materialMapper;
    @PersistenceContext
    private EntityManager entityManager;
    private OrganizationRepository organizationRepository;
    private AuthRepository authRepository;
    private MaterialRepository materialRepository;

    @Transactional
    public List<MaterialPayload> findAllByOrganization(String organizationCode, String userEmail) {
        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var user = authRepository.findByEmail(userEmail).orElse(new User());
        var materials = materialRepository.findAllByOrganization(organization);

        if (organization.isAdminOrUser(user) && materials.isPresent()) {
            return materials.get().stream().map(materialMapper::toPayload).collect(Collectors.toList());
        }
        return List.of();
    }

    @Transactional
    public ActionResult addMaterial(String organizationCode, String userEmail, SimpleMaterialPayload payload) {
        payload.enrichWith(organizationCode);

        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var material = materialRepository.findByQrCode(payload.getQrCode());
        var user = authRepository.findByEmail(userEmail).orElse(new User());

        if (material.isEmpty() && organization.isAdmin(user)) {
            organization.addMaterials(simpleMaterialMapper.toEntity(payload));

            entityManager.merge(organization);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("Error adding material");
    }

    @Transactional
    public ActionResult removeMaterial(String organizationCode, String userEmail, String qrCode) {
        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var material = materialRepository.findByQrCode(qrCode);
        var user = authRepository.findByEmail(userEmail).orElse(new User());

        if (material.isPresent() && organization.isAdmin(user) && organization.hasMaterial(material.get())) {
            organization.removeMaterials(material.get());

            entityManager.merge(organization);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("Error removing material");
    }
}
