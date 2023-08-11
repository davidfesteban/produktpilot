package dev.misei.application;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.Stand;
import dev.misei.domain.entity.User;
import dev.misei.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StandProcessor {

    private final OrganizationRepository organizationRepository;

    public Organization create(String warehouseId, Stand entity, Organization org, User user) {
        var warehouse = organizationRepository.findByWarehouses_NameIgnoreCase(warehouseId).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (warehouse.getStands().stream().anyMatch(stand -> stand.equals(entity))) {
            throw ProduktPilotException.Type.RESOURCE_ALREADY_EXISTS.boom();
        }

        if (!user.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        org.getWarehouses().remove(warehouse);
        warehouse.addStand(entity);

        return organizationRepository.save(org.addWarehouse(warehouse));
    }

    public Organization modify(String warehouseId, Stand entity, Organization org, User user) {
        var warehouse = organizationRepository.findByWarehouses_NameIgnoreCase(warehouseId).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (warehouse.getStands().stream().noneMatch(stand -> stand.equals(entity))) {
            throw ProduktPilotException.Type.RESOURCE_NOT_FOUND.boom();
        } else if (!user.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        org.getWarehouses().remove(warehouse);
        warehouse.setStands(warehouse.getStands().stream().filter(stand -> !stand.equals(entity)).collect(Collectors.toSet()));
        warehouse.getStands().add(entity);

        return organizationRepository.save(org.addWarehouse(warehouse));
    }

    public Organization delete(String warehouseId, String standId, Organization org, User requester) {
        var warehouse = organizationRepository.findByWarehouses_NameIgnoreCase(warehouseId).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        var stand = warehouse.getStands().stream().filter(stand1 -> stand1.getId().equalsIgnoreCase(standId)).findFirst().orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (!requester.isAdmin()) {
            throw ProduktPilotException.Type.NOT_ENOUGH_PRIVILEGES.boom();
        }

        org.getWarehouses().remove(warehouse);
        warehouse.getStands().remove(stand);

        return organizationRepository.save(org.addWarehouse(warehouse));
    }
}
