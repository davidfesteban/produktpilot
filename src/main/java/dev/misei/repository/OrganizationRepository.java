package dev.misei.repository;

import dev.misei.domain.entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String> {
    Optional<Billing> findByBillings_TimestampWhen(long timestampWhen);
    Optional<Stand> findByWarehouses_Stands_IdIgnoreCase(String id);
    Optional<Warehouse> findByWarehouses_NameIgnoreCase(String name);
    Optional<User> findByUsers_UserNameIgnoreCase(String userName);

}
