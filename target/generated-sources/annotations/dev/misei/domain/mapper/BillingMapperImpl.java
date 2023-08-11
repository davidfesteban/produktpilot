package dev.misei.domain.mapper;

import dev.misei.domain.entity.Billing;
import dev.misei.domain.entity.Material;
import dev.misei.domain.entity.Stand;
import dev.misei.domain.entity.User;
import dev.misei.domain.entity.UserRole;
import dev.misei.domain.entity.Warehouse;
import dev.misei.domain.payload.BillingPayload;
import dev.misei.domain.payload.MaterialPayload;
import dev.misei.domain.payload.StandPayload;
import dev.misei.domain.payload.UserPayload;
import dev.misei.domain.payload.WarehousePayload;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-11T01:39:41+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class BillingMapperImpl implements BillingMapper {

    @Override
    public Billing toEntity(BillingPayload payload) {
        if ( payload == null ) {
            return null;
        }

        Billing billing = new Billing();

        billing.setWarehouseCopyWhere( warehousePayloadToWarehouse( payload.warehouseCopyWhere() ) );
        billing.setMaterialCopyWhat( materialPayloadToMaterial( payload.materialCopyWhat() ) );
        billing.setUserCopyWho( userPayloadToUser( payload.userCopyWho() ) );
        billing.setTimestampWhen( payload.timestampWhen() );

        return billing;
    }

    @Override
    public BillingPayload toPayload(Billing entity) {
        if ( entity == null ) {
            return null;
        }

        String id = null;
        WarehousePayload warehouseCopyWhere = null;
        MaterialPayload materialCopyWhat = null;
        UserPayload userCopyWho = null;
        long timestampWhen = 0L;

        id = entity.getId();
        warehouseCopyWhere = warehouseToWarehousePayload( entity.getWarehouseCopyWhere() );
        materialCopyWhat = materialToMaterialPayload( entity.getMaterialCopyWhat() );
        userCopyWho = userToUserPayload( entity.getUserCopyWho() );
        timestampWhen = entity.getTimestampWhen();

        BillingPayload billingPayload = new BillingPayload( id, warehouseCopyWhere, materialCopyWhat, userCopyWho, timestampWhen );

        return billingPayload;
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

    protected Stand standPayloadToStand(StandPayload standPayload) {
        if ( standPayload == null ) {
            return null;
        }

        Stand stand = new Stand();

        stand.setId( standPayload.id() );
        stand.setMaterials( materialPayloadSetToMaterialSet( standPayload.materials() ) );

        return stand;
    }

    protected Set<Stand> standPayloadSetToStandSet(Set<StandPayload> set) {
        if ( set == null ) {
            return null;
        }

        Set<Stand> set1 = new LinkedHashSet<Stand>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( StandPayload standPayload : set ) {
            set1.add( standPayloadToStand( standPayload ) );
        }

        return set1;
    }

    protected Warehouse warehousePayloadToWarehouse(WarehousePayload warehousePayload) {
        if ( warehousePayload == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        warehouse.setName( warehousePayload.name() );
        warehouse.setGeneric( warehousePayload.generic() );
        warehouse.setStands( standPayloadSetToStandSet( warehousePayload.stands() ) );

        return warehouse;
    }

    protected User userPayloadToUser(UserPayload userPayload) {
        if ( userPayload == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userPayload.userName() );
        user.setFullName( userPayload.fullName() );
        user.setPassword( userPayload.password() );
        user.setBase64Image( userPayload.base64Image() );
        user.setPhone( userPayload.phone() );
        if ( userPayload.userRole() != null ) {
            user.setUserRole( Enum.valueOf( UserRole.class, userPayload.userRole() ) );
        }

        return user;
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

    protected StandPayload standToStandPayload(Stand stand) {
        if ( stand == null ) {
            return null;
        }

        String id = null;
        Set<MaterialPayload> materials = null;

        id = stand.getId();
        materials = materialSetToMaterialPayloadSet( stand.getMaterials() );

        StandPayload standPayload = new StandPayload( id, materials );

        return standPayload;
    }

    protected Set<StandPayload> standSetToStandPayloadSet(Set<Stand> set) {
        if ( set == null ) {
            return null;
        }

        Set<StandPayload> set1 = new LinkedHashSet<StandPayload>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Stand stand : set ) {
            set1.add( standToStandPayload( stand ) );
        }

        return set1;
    }

    protected WarehousePayload warehouseToWarehousePayload(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        String name = null;
        boolean generic = false;
        Set<StandPayload> stands = null;

        name = warehouse.getName();
        generic = warehouse.isGeneric();
        stands = standSetToStandPayloadSet( warehouse.getStands() );

        WarehousePayload warehousePayload = new WarehousePayload( name, generic, stands );

        return warehousePayload;
    }

    protected UserPayload userToUserPayload(User user) {
        if ( user == null ) {
            return null;
        }

        String userName = null;
        String fullName = null;
        String password = null;
        String base64Image = null;
        String phone = null;
        String userRole = null;

        userName = user.getUserName();
        fullName = user.getFullName();
        password = user.getPassword();
        base64Image = user.getBase64Image();
        phone = user.getPhone();
        if ( user.getUserRole() != null ) {
            userRole = user.getUserRole().name();
        }

        UserPayload userPayload = new UserPayload( userName, fullName, password, base64Image, phone, userRole );

        return userPayload;
    }
}
