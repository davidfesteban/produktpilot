package dev.misei.application;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.User;
import dev.misei.domain.entity.UserRole;
import dev.misei.repository.OrganizationRepository;
import dev.misei.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class OrganizationProcessor {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;


    public Organization createFirstTimeOrganization(Organization entity, User user) {
        if (organizationRepository.count() != 0) {
            throw ProduktPilotException.Type.RESOURCE_ALREADY_EXISTS.boom();
        }

        user.setUserRole(UserRole.ADMIN);
        entity.setUsers(Set.of(user));

        userRepository.save(user);
        return organizationRepository.save(entity);
    }

    public Organization details(User user) {
        if (user.getUserRole() != UserRole.ADMIN) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        return organizationRepository.findByUsers_UserNameIgnoreCase(user.getUserName()).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
    }
}
