package es.misei.everi.application;

import es.misei.everi.domain.entity.Project;
import es.misei.everi.domain.entity.User;
import es.misei.everi.domain.mapper.BillingMapper;
import es.misei.everi.domain.payload.ActionResult;
import es.misei.everi.domain.payload.BillingPayload;
import es.misei.everi.repository.AuthRepository;
import es.misei.everi.repository.BillingRepository;
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
public class BillingProcessor {
    private final BillingMapper billingMapper;
    @PersistenceContext
    private EntityManager entityManager;
    private AuthRepository authRepository;
    private BillingRepository billingRepository;
    private ProjectRepository projectRepository;

    @Transactional
    public List<BillingPayload> findAllByProject(String projectCode, String userEmail) {
        var project = projectRepository.findByCode(projectCode).orElse(new Project());
        var user = authRepository.findByEmail(userEmail).orElse(new User());
        var billings = billingRepository.findAllByProject(project);

        if (project.getOrganization().isAdminOrUser(user) && billings.isPresent()) {
            return billings.get().stream().map(billingMapper::toPayload).collect(Collectors.toList());
        }
        return List.of();
    }

    @Transactional
    public ActionResult addBilling(String projectCode, String userEmail, BillingPayload payload) {
        var project = projectRepository.findByCode(projectCode).orElse(new Project());
        var user = authRepository.findByEmail(userEmail).orElse(new User());
        //var billings = billingRepository.findByTimestampWhenAndPriceAndUserEmail(BillingMapper.INSTANCE.toEntity(payload.setUser(userEmail)));

        if (/*billings.isEmpty() && */project.getOrganization().isAdminOrUser(user)) {
            project.addBilling(billingMapper.toEntity(payload.setUser(userEmail)));

            entityManager.merge(project);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("Error adding billing");
    }

    @Transactional
    public ActionResult removeBilling(String projectCode, String userEmail, Long id) {
        var project = projectRepository.findByCode(projectCode).orElse(new Project());
        var user = authRepository.findByEmail(userEmail).orElse(new User());
        var billings = billingRepository.findById(id);

        if (billings.isPresent() &&
                ((project.getOrganization().isUser(user) && billings.get().getUserEmail().equalsIgnoreCase(userEmail)) ||
                        (project.getOrganization().isAdmin(user)))) {
            project.removeBilling(billings.get());

            entityManager.merge(project);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("Error removing billing");
    }
}
