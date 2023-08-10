package es.misei.everi.domain.mapper.material;

import es.misei.everi.domain.entity.Material;
import es.misei.everi.domain.payload.material.SimpleMaterialPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SimpleMaterialMapper {

    SimpleMaterialMapper INSTANCE = Mappers.getMapper(SimpleMaterialMapper.class);

    Material toEntity(SimpleMaterialPayload payload);

    SimpleMaterialPayload toPayload(Material entity);
}
