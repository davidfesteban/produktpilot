package dev.misei.domain.mapper;

import dev.misei.domain.entity.Material;
import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.User;
import dev.misei.domain.payload.MaterialPayload;
import dev.misei.domain.payload.OrganizationPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {
    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    Organization toEntity(OrganizationPayload payload);

    OrganizationPayload toPayload(Organization entity);
}
