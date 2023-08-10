package es.misei.everi.repository;

import es.misei.everi.domain.entity.Billing;
import es.misei.everi.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
    //Optional<Billing> findByTimestampWhenAndPriceAndUserEmail(Billing billing);
    Optional<List<Billing>> findAllByProject(Project project);
}
