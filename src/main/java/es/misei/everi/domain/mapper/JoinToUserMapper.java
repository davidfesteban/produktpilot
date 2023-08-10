package es.misei.everi.domain.mapper;

import es.misei.everi.domain.entity.User;
import es.misei.everi.domain.payload.user.UserPayload;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.function.Function;

@AllArgsConstructor
public class JoinToUserMapper implements Function<UserPayload, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User apply(UserPayload payload) {
        var user = new User();
        user.setEmail(payload.getEmail());
        user.setName(payload.getName());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        return user;
    }
}
