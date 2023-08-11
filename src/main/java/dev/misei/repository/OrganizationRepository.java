package dev.misei.repository;

import dev.misei.domain.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {
    Optional<Organization> findByUsers_UserNameIgnoreCase(String userName);

}
