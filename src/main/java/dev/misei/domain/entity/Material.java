package dev.misei.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    //FEATURE: Or manually inserted via QR
    private String id;
    private String name;
    private String description;
    private String retailerPrice;
    private String ourPrice;

    //FEATURE If there are multiple items with the same id because you dont want to QR or ID each one of them
    private boolean generic;
    //Then you look up this.
    private int quantity;

    @Nullable
    private String base64Image;


    @Override
    public boolean equals(Object o) {
        return (o instanceof Material that) && this.id.equalsIgnoreCase(that.id);
    }

}
