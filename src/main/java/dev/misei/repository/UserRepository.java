package dev.misei.repository;

import dev.misei.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUserNameIgnoreCase(@NonNull String userName);

}
