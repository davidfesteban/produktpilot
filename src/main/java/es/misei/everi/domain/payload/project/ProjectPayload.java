package es.misei.everi.domain.payload.project;

import es.misei.everi.domain.payload.BillingPayload;
import es.misei.everi.domain.payload.organization.SimpleOrganizationPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectPayload {
    private String name;
    private String code;
    private String stage;
    private String urlImage;
    private long timestampStart;
    private long timestampEnd;

    private SimpleOrganizationPayload organization;
    private List<BillingPayload> billings;

    public ProjectPayload enrichWith(String organization) {
        this.code = organization + "_" + this.code;
        return this;
    }
}
