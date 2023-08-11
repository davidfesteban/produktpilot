package dev.misei.application;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.User;
import dev.misei.domain.entity.Warehouse;
import dev.misei.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseProcessor {

    private final OrganizationRepository organizationRepository;

    public Organization create(Warehouse entity, Organization org, User user) {
        if (org.getWarehouses().stream().anyMatch(warehouse -> warehouse.equals(entity))) {
            throw ProduktPilotException.Type.RESOURCE_ALREADY_EXISTS.boom();
        }

        if (!user.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        return organizationRepository.save(org.addWarehouse(entity));
    }

    public Organization modify(Warehouse entity, Organization org, User user) {
        if (org.getWarehouses().stream().noneMatch(warehouse -> warehouse.equals(entity))) {
            throw ProduktPilotException.Type.RESOURCE_NOT_FOUND.boom();
        } else if (!user.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        var warehouses = org.getWarehouses().stream().filter(warehouse -> !warehouse.equals(entity)).collect(Collectors.toSet());
        warehouses.add(entity);
        org.setWarehouses(warehouses);

        return organizationRepository.save(org);
    }

    public Organization delete(String id, Organization org, User requester) {
        var warehouse = organizationRepository.findByWarehouses_NameIgnoreCase(id).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (!requester.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        org.getWarehouses().remove(warehouse);
        return organizationRepository.save(org);
    }
}
