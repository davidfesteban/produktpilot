package es.misei.everi.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingPayload {
    private String materialName;
    private String description;
    private String price;
    private long timestampWhen;
    private String userEmail;

    public BillingPayload setUser(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }
}
