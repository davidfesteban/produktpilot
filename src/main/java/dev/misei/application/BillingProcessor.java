package dev.misei.application;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.Billing;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.User;
import dev.misei.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BillingProcessor {

    private final OrganizationRepository organizationRepository;

    public Organization create(Billing entity, Organization org, User user) {
        if (org.getBillings().stream().anyMatch(that -> that.equals(entity))) {
            throw ProduktPilotException.Type.RESOURCE_ALREADY_EXISTS.boom();
        }

        return organizationRepository.save(org.addBilling(entity));
    }

    public Organization modify(Billing entity, Organization org, User user) {
        if (org.getBillings().stream().anyMatch(that -> that.equals(entity)) && (user.isAdmin() || entity.getUserCopyWho().equals(user))) {
            var billings = org.getBillings().stream().filter(that -> !that.equals(entity)).collect(Collectors.toSet());
            billings.add(entity);
            org.setBillings(billings);

            return organizationRepository.save(org);
        }

        throw ProduktPilotException.Type.RESOURCE_NOT_FOUND.boom();
    }

    public Organization delete(long timestamp, Organization org, User requester) {
        var billing = organizationRepository.findByBillings_TimestampWhenProxy(timestamp).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (requester.isAdmin() || billing.getUserCopyWho().equals(requester)) {
            org.getBillings().remove(billing);
            return organizationRepository.save(org);
        }

        throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
    }
}
