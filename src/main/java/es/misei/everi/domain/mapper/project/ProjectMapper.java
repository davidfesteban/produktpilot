package es.misei.everi.domain.mapper.project;

import es.misei.everi.domain.entity.Project;
import es.misei.everi.domain.payload.project.ProjectPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project toEntity(ProjectPayload payload);

    ProjectPayload toPayload(Project entity);
}
