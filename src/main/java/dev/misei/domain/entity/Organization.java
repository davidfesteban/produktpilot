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

    @DocumentReference
    private Set<User> users = new HashSet<>();

    @DocumentReference
    private Set<Warehouse> warehouses;

    @DocumentReference
    private Set<Billing> billing;

}
