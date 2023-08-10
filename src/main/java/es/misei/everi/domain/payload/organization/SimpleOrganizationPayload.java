package es.misei.everi.domain.payload.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleOrganizationPayload {
    private String name;
    private String code;
    private String urlImage;
}
