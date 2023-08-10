package es.misei.everi.repository;

import es.misei.everi.domain.entity.Organization;
import es.misei.everi.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByCode(String code);

    Optional<List<Project>> findAllByOrganization(Organization organization);
}
