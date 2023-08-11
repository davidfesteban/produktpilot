package dev.misei.application;

import dev.misei.domain.ProduktPilotException;
import dev.misei.domain.entity.Material;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.User;
import dev.misei.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialProcessor {

    private final OrganizationRepository organizationRepository;

    public Organization create(String warehouseId, String standId, Material entity, Organization org, User user) {
        var warehouse = organizationRepository.findByWarehouses_NameIgnoreCaseProxy(warehouseId).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        var stand = organizationRepository.findByWarehouses_Stands_IdIgnoreCaseProxy(warehouse, standId).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (stand.getMaterials().stream().anyMatch(that -> that.equals(entity))) {
            throw ProduktPilotException.Type.RESOURCE_ALREADY_EXISTS.boom();
        }

        org.getWarehouses().remove(warehouse);
        warehouse.getStands().remove(stand);
        stand.addMaterial(entity);
        warehouse.addStand(stand);

        return organizationRepository.save(org.addWarehouse(warehouse));
    }

    public Organization modify(String warehouseId, String standId, Material entity, Organization org, User user) {
        var warehouse = organizationRepository.findByWarehouses_NameIgnoreCaseProxy(warehouseId).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        var stand = organizationRepository.findByWarehouses_Stands_IdIgnoreCaseProxy(warehouse, standId).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        if (stand.getMaterials().stream().noneMatch(that -> that.equals(entity))) {
            throw ProduktPilotException.Type.RESOURCE_NOT_FOUND.boom();
        }

        org.getWarehouses().remove(warehouse);
        warehouse.getStands().remove(stand);
        stand.setMaterials(stand.getMaterials().stream().filter(material -> !material.equals(entity)).collect(Collectors.toSet()));
        stand.addMaterial(entity);
        warehouse.addStand(stand);

        return organizationRepository.save(org.addWarehouse(warehouse));
    }

    public Organization delete(String warehouseId, String standId, String materialId, Organization org, User user) {
        var warehouse = organizationRepository.findByWarehouses_NameIgnoreCaseProxy(warehouseId).orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        var stand = warehouse.getStands().stream().filter(stand1 -> stand1.getId().equalsIgnoreCase(standId)).findFirst().orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);
        var material = stand.getMaterials().stream().filter(material1 -> material1.getId().equalsIgnoreCase(materialId)).findFirst().orElseThrow(ProduktPilotException.Type.RESOURCE_NOT_FOUND::boom);

        org.getWarehouses().remove(warehouse);
        warehouse.getStands().remove(stand);
        stand.getMaterials().remove(material);
        warehouse.getStands().add(stand);

        return organizationRepository.save(org.addWarehouse(warehouse));
    }
}
