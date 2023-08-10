package es.misei.everi.repository;

import es.misei.everi.domain.entity.Material;
import es.misei.everi.domain.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Optional<Material> findByQrCode(String qrCode);

    boolean existsByQrCode(String qrCode);

    Optional<List<Material>> findAllByOrganization(Organization organization);
}
