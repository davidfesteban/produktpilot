package dev.misei.domain.mapper;

import dev.misei.domain.entity.Material;
import dev.misei.domain.payload.MaterialPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialMapper {
    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    Material toEntity(MaterialPayload payload);

    MaterialPayload toPayload(Material entity);
}
