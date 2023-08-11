package dev.misei.domain.mapper;

import dev.misei.domain.entity.Organization;
import dev.misei.domain.payload.OrganizationPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    Organization toEntity(OrganizationPayload payload);

    OrganizationPayload toPayload(Organization entity);
}
