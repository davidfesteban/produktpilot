package es.misei.everi.domain.mapper.project;

import es.misei.everi.domain.entity.Project;
import es.misei.everi.domain.payload.project.SimpleProjectPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SimpleProjectMapper {
    SimpleProjectMapper INSTANCE = Mappers.getMapper(SimpleProjectMapper.class);

    Project toEntity(SimpleProjectPayload payload);

    SimpleProjectPayload toPayload(Project entity);
}
