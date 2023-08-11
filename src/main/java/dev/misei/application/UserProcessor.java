package dev.misei.application;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.User;
import dev.misei.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserProcessor {

    private final OrganizationRepository organizationRepository;

    public Organization create(User entity, Organization org, User user) {
        if (org.getUsers().stream().anyMatch(user1 -> user1.equals(entity))) {
            throw ProduktPilotException.Type.RESOURCE_ALREADY_EXISTS.boom();
        }

        if (!user.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        return organizationRepository.save(org.addUser(entity));
    }

    public Organization modify(User entity, Organization org, User user) {
        if (entity.equals(user) && entity.compareTo(user) != 0) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        } else if (!user.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        var users = org.getUsers().stream().filter(user1 -> !user1.equals(entity)).collect(Collectors.toSet());
        users.add(entity);
        org.setUsers(users);

        return organizationRepository.save(org);
    }

    public Organization delete(String userName, Organization org, User requester) {
        var user = organizationRepository.findByUsers_UserNameIgnoreCase(userName).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (!requester.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        org.getUsers().remove(user);
        return organizationRepository.save(org);
    }
}
