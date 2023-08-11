package dev.misei.repository;

import dev.misei.domain.entity.Stand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StandRepository extends MongoRepository<Stand, String> {

    Optional<Stand> findByIdIgnoreCase(String id);

}
