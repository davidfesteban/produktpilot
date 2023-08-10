package es.misei.everi.domain.payload.project;

import es.misei.everi.domain.entity.Billing;
import es.misei.everi.domain.entity.Organization;
import es.misei.everi.domain.payload.BillingPayload;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleProjectPayload {
    private String name;
    private String code;
    private String stage;
    private String urlImage;
    private long timestampStart;
    private long timestampEnd;

    public SimpleProjectPayload enrichWith(String organization) {
        this.code = organization + "_" + this.code;
        return this;
    }
}
