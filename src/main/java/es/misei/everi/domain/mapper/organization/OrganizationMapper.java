package es.misei.everi.domain.mapper.organization;

import es.misei.everi.domain.entity.Organization;
import es.misei.everi.domain.payload.organization.OrganizationPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    Organization toEntity(OrganizationPayload payload);

    OrganizationPayload toPayload(Organization entity);
}
