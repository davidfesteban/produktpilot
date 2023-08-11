package dev.misei.domain.mapper;

import dev.misei.domain.entity.Material;
import dev.misei.domain.payload.MaterialPayload;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T01:39:42+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class MaterialMapperImpl implements MaterialMapper {

    @Override
    public Material toEntity(MaterialPayload payload) {
        if ( payload == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( payload.id() );
        material.setName( payload.name() );
        material.setDescription( payload.description() );
        material.setRetailerPrice( payload.retailerPrice() );
        material.setOurPrice( payload.ourPrice() );
        material.setGeneric( payload.generic() );
        material.setQuantity( payload.quantity() );
        material.setBase64Image( payload.base64Image() );

        return material;
    }

    @Override
    public MaterialPayload toPayload(Material entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        String name = null;
        String description = null;
        String retailerPrice = null;
        String ourPrice = null;
        boolean generic = false;
        int quantity = 0;
        String base64Image = null;

        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        retailerPrice = entity.getRetailerPrice();
        ourPrice = entity.getOurPrice();
        generic = entity.isGeneric();
        quantity = entity.getQuantity();
        base64Image = entity.getBase64Image();

        MaterialPayload materialPayload = new MaterialPayload( id, name, description, retailerPrice, ourPrice, generic, quantity, base64Image );

        return materialPayload;
    }
}
