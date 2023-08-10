package es.misei.everi.domain.mapper.organization;

import es.misei.everi.domain.entity.Organization;
import es.misei.everi.domain.payload.organization.SimpleOrganizationPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SimpleOrganizationMapper {

    SimpleOrganizationMapper INSTANCE = Mappers.getMapper(SimpleOrganizationMapper.class);

    Organization toEntity(SimpleOrganizationPayload payload);

    SimpleOrganizationPayload toPayload(Organization entity);
}
