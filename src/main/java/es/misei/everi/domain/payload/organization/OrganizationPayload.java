package es.misei.everi.domain.payload.organization;

import es.misei.everi.domain.payload.material.SimpleMaterialPayload;
import es.misei.everi.domain.payload.project.SimpleProjectPayload;
import es.misei.everi.domain.payload.user.SimpleUserPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationPayload {
    private String name;
    private String code;
    private String urlImage;

    //TODO: Add country, direcction...
    private Set<SimpleUserPayload> waitlist;
    private Set<SimpleUserPayload> users;
    private Set<SimpleUserPayload> admins;
    private Set<SimpleProjectPayload> projects;
    private Set<SimpleMaterialPayload> materials;
}
