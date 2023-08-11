package dev.misei.repository;

import dev.misei.domain.entity.Warehouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends MongoRepository<Warehouse, String> {
    Optional<Warehouse> findByNameIgnoreCase(String name);

}
