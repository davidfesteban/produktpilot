package dev.misei.domain.mapper;

import dev.misei.domain.entity.Material;
import dev.misei.domain.entity.Stand;
import dev.misei.domain.payload.MaterialPayload;
import dev.misei.domain.payload.StandPayload;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T01:39:41+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class StandMapperImpl implements StandMapper {

    @Override
    public Stand toEntity(StandPayload payload) {
        if ( payload == null ) {
            return null;
        }

        Stand stand = new Stand();

        stand.setId( payload.id() );
        stand.setMaterials( materialPayloadSetToMaterialSet( payload.materials() ) );

        return stand;
    }

    @Override
    public StandPayload toPayload(Stand entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        Set<MaterialPayload> materials = null;

        id = entity.getId();
        materials = materialSetToMaterialPayloadSet( entity.getMaterials() );

        StandPayload standPayload = new StandPayload( id, materials );

        return standPayload;
    }

    protected Material materialPayloadToMaterial(MaterialPayload materialPayload) {
        if ( materialPayload == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( materialPayload.id() );
        material.setName( materialPayload.name() );
        material.setDescription( materialPayload.description() );
        material.setRetailerPrice( materialPayload.retailerPrice() );
        material.setOurPrice( materialPayload.ourPrice() );
        material.setGeneric( materialPayload.generic() );
        material.setQuantity( materialPayload.quantity() );
        material.setBase64Image( materialPayload.base64Image() );

        return material;
    }

    protected Set<Material> materialPayloadSetToMaterialSet(Set<MaterialPayload> set) {
        if ( set == null ) {
            return null;
        }

        Set<Material> set1 = new LinkedHashSet<Material>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MaterialPayload materialPayload : set ) {
            set1.add( materialPayloadToMaterial( materialPayload ) );
        }

        return set1;
    }

    protected MaterialPayload materialToMaterialPayload(Material material) {
        if ( material == null ) {
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

        id = material.getId();
        name = material.getName();
        description = material.getDescription();
        retailerPrice = material.getRetailerPrice();
        ourPrice = material.getOurPrice();
        generic = material.isGeneric();
        quantity = material.getQuantity();
        base64Image = material.getBase64Image();

        MaterialPayload materialPayload = new MaterialPayload( id, name, description, retailerPrice, ourPrice, generic, quantity, base64Image );

        return materialPayload;
    }

    protected Set<MaterialPayload> materialSetToMaterialPayloadSet(Set<Material> set) {
        if ( set == null ) {
            return null;
        }

        Set<MaterialPayload> set1 = new LinkedHashSet<MaterialPayload>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Material material : set ) {
            set1.add( materialToMaterialPayload( material ) );
        }

        return set1;
    }
}
