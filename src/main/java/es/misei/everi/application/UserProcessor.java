package es.misei.everi.application;

import es.misei.everi.domain.entity.Organization;
import es.misei.everi.domain.entity.User;
import es.misei.everi.domain.mapper.user.UserMapper;
import es.misei.everi.domain.payload.ActionResult;
import es.misei.everi.domain.payload.user.UserPayload;
import es.misei.everi.repository.AuthRepository;
import es.misei.everi.repository.OrganizationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProcessor {
    private final UserMapper userMapper;
    @PersistenceContext
    private EntityManager entityManager;
    private OrganizationRepository organizationRepository;
    private AuthRepository authRepository;

    @Transactional
    public UserPayload findByEmail(String email) {
        var user = authRepository.findByEmail(email);

        if (user.isPresent()) {
            return userMapper.toPayload(user.get());
        }

        return new UserPayload();
    }

    @Transactional
    public ActionResult addUserToWaitlist(String organizationCode, String userEmail) {
        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var user = authRepository.findByEmail(userEmail);

        if (user.isPresent() && !organization.isAdminOrUser(user.get())) {
            organization.addUserToWaitlist(user.get());

            entityManager.merge(organization);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("Cannot join wait-list");
    }

    @Transactional
    public ActionResult addUser(String organizationCode, String adminUserEmail, String userEmail, boolean asAdmin) {
        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var admin = authRepository.findByEmail(adminUserEmail).orElse(new User());
        var user = authRepository.findByEmail(userEmail);

        if (user.isPresent() && organization.isAdmin(admin) && !organization.isUser(user.get())) {
            organization.removeUserFromWaitlist(user.get());

            if (asAdmin) {
                organization.addAdmin(user.get());
            } else {
                organization.addUser(user.get());
            }


            entityManager.merge(organization);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("User already exist");
    }

    @Transactional
    public ActionResult removeUser(String organizationCode, String userEmail) {
        var organization = organizationRepository.findByCode(organizationCode).orElseGet(Organization::new);
        var user = authRepository.findByEmail(userEmail);

        if (user.isPresent() && organization.isUser(user.get())) {

            organization.removeUser(user.get());

            entityManager.merge(organization);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("User not found");
    }

    @Transactional
    public ActionResult removeUserAsAdmin(String organizationCode, String adminUserEmail, String userEmail) {
        var organization = organizationRepository.findByCode(organizationCode).orElse(new Organization());
        var admin = authRepository.findByEmail(adminUserEmail).orElse(new User());
        var user = authRepository.findByEmail(userEmail).orElse(new User());

        if (organization.isAdmin(admin) && organization.isUser(user)) {
            organization.removeUser(user);

            entityManager.merge(organization);
            entityManager.flush();
            return ActionResult.TRUE();
        }

        return ActionResult.FALSE("User not found or you are not an admin");
    }
}
