package es.misei.everi.repository;

import es.misei.everi.domain.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByCode(String code);

    Boolean existsByCode(String code);

    void deleteByCode(String code);
}
