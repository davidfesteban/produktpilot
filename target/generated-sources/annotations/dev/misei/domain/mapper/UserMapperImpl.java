package dev.misei.domain.mapper;

import dev.misei.domain.entity.User;
import dev.misei.domain.payload.UserPayload;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T01:39:41+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserPayload payload) {
        if ( payload == null ) {
            return null;
        }

        User user = new User();

        user.setUserRole( mapUserRoleToEnum( payload.userRole() ) );
        user.setUserName( payload.userName() );
        user.setFullName( payload.fullName() );
        user.setPassword( payload.password() );
        user.setBase64Image( payload.base64Image() );
        user.setPhone( payload.phone() );

        return user;
    }

    @Override
    public UserPayload toPayload(User entity) {
        if ( entity == null ) {
            return null;
        }

        String userRole = null;
        String userName = null;
        String fullName = null;
        String base64Image = null;
        String phone = null;

        userRole = mapUserRoleToString( entity.getUserRole() );
        userName = entity.getUserName();
        fullName = entity.getFullName();
        base64Image = entity.getBase64Image();
        phone = entity.getPhone();

        String password = null;

        UserPayload userPayload = new UserPayload( userName, fullName, password, base64Image, phone, userRole );

        return userPayload;
    }
}
