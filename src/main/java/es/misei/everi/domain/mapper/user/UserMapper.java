package es.misei.everi.domain.mapper.user;

import es.misei.everi.domain.entity.User;
import es.misei.everi.domain.payload.user.UserPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserPayload payload);

    UserPayload toPayload(User entity);
}
