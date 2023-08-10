package es.misei.everi.domain.entity;

import es.misei.everi.domain.payload.ActionResult;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String urlImage;

    @ManyToMany(targetEntity = User.class, mappedBy = "waitlistOrganizations", cascade = CascadeType.MERGE)
    private Set<User> waitlist = new HashSet<>();

    @ManyToMany(targetEntity = User.class, mappedBy = "organizations", cascade = CascadeType.MERGE)
    private Set<User> users = new HashSet<>();

    @ManyToMany(targetEntity = User.class, mappedBy = "adminOrganizations", cascade = CascadeType.MERGE)
    private Set<User> admins = new HashSet<>();

    @OneToMany(targetEntity = Project.class, mappedBy = "organization", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(targetEntity = Material.class, mappedBy = "organization", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Material> materials = new HashSet<>();

    public ActionResult addUser(User user) {
        users.add(user);
        user.addOrganization(this);
        return ActionResult.TRUE();
    }

    public ActionResult addAdmin(User user) {
        admins.add(user);
        user.addAdminOrganization(this);
        return ActionResult.TRUE();
    }

    public boolean isUser(User user) {
        return users.contains(user) && user.getOrganizations().contains(this);
    }

    public boolean isAdmin(User user) {
        return admins.contains(user) && user.getAdminOrganizations().contains(this);
    }

    public boolean isAdminOrUser(User user) {
        return isUser(user) || isAdmin(user);
    }

    public ActionResult removeUser(User user) {
        users.remove(user);
        user.removeOrganization(this);
        return ActionResult.TRUE();
    }

    public ActionResult removeAdmin(User user) {
        admins.remove(user);
        user.removeAdminOrganization(this);
        return ActionResult.TRUE();
    }

    public ActionResult addMaterials(Material material) {
        materials.add(material);
        material.setOrganization(this);
        return ActionResult.TRUE();
    }

    public ActionResult removeMaterials(Material material) {
        materials.remove(material);
        material.setOrganization(null);
        return ActionResult.TRUE();
    }

    public ActionResult addProject(Project project) {
        projects.add(project);
        project.setOrganization(this);
        return ActionResult.TRUE();
    }

    public ActionResult removeProject(Project project) {
        projects.remove(project);
        project.setOrganization(null);
        return ActionResult.TRUE();
    }

    public ActionResult addUserToWaitlist(User user) {
        waitlist.add(user);
        user.addWaitlistOrganization(this);
        return ActionResult.TRUE();
    }

    public ActionResult removeUserFromWaitlist(User user) {
        waitlist.remove(user);
        user.removeWaitlistOrganization(this);
        return ActionResult.TRUE();
    }

    public boolean hasProject(Project project) {
        return projects.contains(project);
    }

    public boolean hasMaterial(Material material) {
        return materials.contains(material);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Organization that = (Organization) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
