package es.misei.everi.domain.payload.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMaterialPayload {
    private String name;
    private String urlImage;
    private String description;
    private String price;
    private String qrCode;

    public SimpleMaterialPayload enrichWith(String organization) {
        this.qrCode = organization + "_" + this.qrCode;
        return this;
    }
}
