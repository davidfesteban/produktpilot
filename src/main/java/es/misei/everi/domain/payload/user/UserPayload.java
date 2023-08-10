package es.misei.everi.domain.payload.user;

import es.misei.everi.domain.payload.organization.SimpleOrganizationPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPayload {
    private String name;
    private String email;
    private String password;

    private List<SimpleOrganizationPayload> organizations;
    private List<SimpleOrganizationPayload> adminOrganizations;
}
