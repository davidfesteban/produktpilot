package dev.misei.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    //FEATURE: Name or Address
    @Id
    private String name;

    //If you don´t care about stand
    private boolean generic;

    private Set<Stand> stands = new HashSet<>();

    public Warehouse addStand(Stand stand) {
        stands.add(stand);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Warehouse that) && this.name.equalsIgnoreCase(that.name);
    }
}
