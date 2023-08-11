package dev.misei.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.HashSet;
import java.util.Set;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    //FEATURE: Name or Address
    @Id
    private String name;

    //If you don´t care about stand
    private boolean generic;

    @DocumentReference
    private Set<Stand> stands = new HashSet<>();
}
