package dev.misei.repository;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {
    default Optional<Billing> findByBillings_TimestampWhenProxy(long timestampWhen) {
        var organization = findByBillings_TimestampWhen(timestampWhen).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        return organization.getBillings().stream().filter(billing -> billing.getTimestampWhen() == timestampWhen).findFirst();
    }

    Optional<Organization> findByBillings_TimestampWhen(long timestampWhen);

    default Optional<Warehouse> findByWarehouses_NameIgnoreCaseProxy(String name) {
        var organization = findByWarehouses_NameIgnoreCase(name).orElseThrow((ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom));
        return organization.getWarehouses().stream().filter(warehouse -> warehouse.getName().equalsIgnoreCase(name)).findFirst();
    }

    Optional<Organization> findByWarehouses_NameIgnoreCase(String name);

    default Optional<User> findByUsers_UserNameIgnoreCaseProxy(String userName) {
        var organization = findByUsers_UserNameIgnoreCase(userName).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        return organization.getUsers().stream().filter(user -> user.getUserName().equalsIgnoreCase(userName)).findFirst();
    }

    Optional<Organization> findByUsers_UserNameIgnoreCase(String userName);

    default Optional<Stand> findByWarehouses_Stands_IdIgnoreCaseProxy(Warehouse warehouse, String standId) {
        return warehouse.getStands().stream().filter(stand -> stand.getId().equalsIgnoreCase(standId)).findFirst();
    }
}
