package dev.misei.domain.mapper;

import dev.misei.domain.entity.Organization;
import dev.misei.domain.entity.Stand;
import dev.misei.domain.payload.OrganizationPayload;
import dev.misei.domain.payload.StandPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StandMapper {
    StandMapper INSTANCE = Mappers.getMapper(StandMapper.class);

    Stand toEntity(StandPayload payload);

    StandPayload toPayload(Stand entity);
}
