package es.misei.everi.domain.mapper.material;

import es.misei.everi.domain.entity.Material;
import es.misei.everi.domain.payload.material.MaterialPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialMapper {

    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    Material toEntity(MaterialPayload payload);

    MaterialPayload toPayload(Material entity);
}
