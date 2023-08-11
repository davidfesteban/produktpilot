package dev.misei.application;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.User;
import dev.misei.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationProcessor {

    private final OrganizationRepository organizationRepository;

    public Organization modifyOrganizationNameAndLicense(String name, String license, Organization org, User user) {
        if (!user.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        organizationRepository.delete(org);
        org.setName(name);
        org.setLicenseCode(license);
        organizationRepository.save(org);

        return organizationRepository.save(org);
    }
}
