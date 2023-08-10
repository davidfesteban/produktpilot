package es.misei.everi.domain.mapper.user;

import es.misei.everi.domain.entity.User;
import es.misei.everi.domain.payload.user.SimpleUserPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SimpleUserMapper {
    SimpleUserMapper INSTANCE = Mappers.getMapper(SimpleUserMapper.class);

    User toEntity(SimpleUserPayload payload);

    SimpleUserPayload toPayload(User entity);
}
