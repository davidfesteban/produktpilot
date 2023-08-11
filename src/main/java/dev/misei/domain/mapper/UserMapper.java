package dev.misei.domain.mapper;

import dev.misei.domain.entity.User;
import dev.misei.domain.entity.UserRole;
import dev.misei.domain.payload.UserPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userRole", source = "userRole", qualifiedByName = "mapUserRoleToEnum")
    User toEntity(UserPayload payload);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "userRole", source = "userRole", qualifiedByName = "mapUserRoleToString")
    UserPayload toPayload(User entity);

    @Named("mapUserRoleToEnum")
    default UserRole mapUserRoleToEnum(String userRole) {
        return userRole != null ? UserRole.valueOf(userRole.toUpperCase()) : UserRole.WORKER;
    }

    @Named("mapUserRoleToString")
    default String mapUserRoleToString(UserRole userRole) {
        return userRole.name();
    }
}
