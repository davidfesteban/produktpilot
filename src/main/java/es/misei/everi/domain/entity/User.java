package es.misei.everi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(targetEntity = Organization.class)
    @JoinTable()
    private List<Organization> organizations = new ArrayList<>();

    @ManyToMany(targetEntity = Organization.class)
    @JoinTable()
    private List<Organization> adminOrganizations = new ArrayList<>();

    @ManyToMany(targetEntity = Organization.class)
    @JoinTable()
    private List<Organization> waitlistOrganizations = new ArrayList<>();

    public void addOrganization(Organization organization) {
        organizations.add(organization);
        organization.getUsers().add(this);
    }

    public void removeOrganization(Organization organization) {
        organizations.remove(organization);
        organization.getUsers().remove(this);
    }

    public void addAdminOrganization(Organization organization) {
        adminOrganizations.add(organization);
        organization.getAdmins().add(this);
    }

    public void removeAdminOrganization(Organization organization) {
        adminOrganizations.remove(organization);
        organization.getAdmins().remove(this);
    }

    public void addWaitlistOrganization(Organization organization) {
        waitlistOrganizations.add(organization);
        organization.getWaitlist().add(this);
    }

    public void removeWaitlistOrganization(Organization organization) {
        waitlistOrganizations.remove(organization);
        organization.getWaitlist().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
