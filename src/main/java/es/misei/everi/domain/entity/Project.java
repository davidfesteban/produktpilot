package es.misei.everi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.misei.everi.domain.payload.ActionResult;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String stage;
    private String urlImage;
    private long timestampStart;
    private long timestampEnd;

    @ManyToOne(targetEntity = Organization.class, cascade = CascadeType.REFRESH)
    private Organization organization;

    @OneToMany(targetEntity = Billing.class, mappedBy = "project", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Billing> billings = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return id != null && Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public ActionResult addBilling(Billing billing) {
        billings.add(billing);
        billing.setProject(this);
        return ActionResult.TRUE();
    }

    public ActionResult removeBilling(Billing billing) {
        billings.remove(billing);
        billing.setProject(null);
        return ActionResult.TRUE();
    }
}
