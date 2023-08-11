package dev.misei.config;

import dev.misei.domain.entity.User;
import dev.misei.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class UserDetailsServiceConfig implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameIgnoreCase(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email: " + email));

        Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority("Default"));

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                authorities);
    }
}
