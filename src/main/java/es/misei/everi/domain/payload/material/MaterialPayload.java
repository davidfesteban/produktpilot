package es.misei.everi.domain.payload.material;

import es.misei.everi.domain.payload.organization.SimpleOrganizationPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialPayload {

    private String name;
    private String urlImage;
    private String description;
    private String price;
    private String qrCode;

    private SimpleOrganizationPayload organization;

    public MaterialPayload enrichWith(String organization) {
        this.qrCode = organization + "_" + this.qrCode;
        return this;
    }
}
