package dev.misei.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.lang.Nullable;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {

    //Only will exist one organization
    @Id
    private String name;

    //FEATURE: introducing license code
    @Nullable
    private String licenseCode;

    private Set<User> users = new HashSet<>();

    private Set<Warehouse> warehouses = new HashSet<>();

    private Set<Billing> billings = new HashSet<>();

    public Organization addUser(User user) {
        users.add(user);
        return this;
    }

    public Organization addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
        return this;
    }

    public Organization addBilling(Billing billing) {
        billings.add(billing);
        return this;
    }
}
