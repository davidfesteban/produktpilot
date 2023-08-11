package dev.misei.repository;

import dev.misei.domain.entity.Material;
import dev.misei.domain.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends MongoRepository<Material, String> {

}
