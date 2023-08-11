package dev.misei.repository;

import dev.misei.domain.entity.Billing;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingRepository extends MongoRepository<Billing, String> {
}
