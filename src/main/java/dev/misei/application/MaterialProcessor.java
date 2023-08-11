package dev.misei.application;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.User;
import dev.misei.repository.OrganizationRepository;
import dev.misei.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaterialProcessor {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public User create(User entity, User user) {
        if (userRepository.findByUserNameIgnoreCase(entity.getUserName()).isPresent()) {
            throw ProduktPilotException.Type.RESOURCE_ALREADY_EXISTS.boom();
        }

        if (!user.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        var organization = organizationRepository.findByUsers_UserNameIgnoreCase(user.getUserName()).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        organization.addUser(user);
        userRepository.save(user);
        organizationRepository.save(organization);

        return entity;
    }

    public User modify(User user, User requester) {
        if (user.equals(requester) && user.compareTo(requester) != 0) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        } else if (!requester.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        var organization = organizationRepository.findByUsers_UserNameIgnoreCase(user.getUserName()).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        organization.addUser(user);
        userRepository.save(user);
        organizationRepository.save(organization);

        return user;
    }

    public User delete(String userName, User requester) {
        var user = userRepository.findByUserNameIgnoreCase(userName).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (!requester.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        var organization = organizationRepository.findByUsers_UserNameIgnoreCase(user.getUserName()).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        organization.getUsers().remove(user);
        userRepository.deleteByUserNameIgnoreCase(userName);
        organizationRepository.save(organization);

        return user;
    }
}
