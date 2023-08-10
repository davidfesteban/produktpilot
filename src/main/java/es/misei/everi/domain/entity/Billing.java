package es.misei.everi.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String materialName;
    private String description;
    private String price;

    private String userEmail;
    private long timestampWhen;

    @ManyToOne(targetEntity = Project.class, cascade = CascadeType.MERGE)
    private Project project;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Billing billing = (Billing) o;
        return id != null && Objects.equals(id, billing.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
