package dev.misei.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billing implements Comparable<Billing> {
    //FEATURE: We could do some insight about price or who sold the most

    private Warehouse warehouseCopyWhere;
    private Material materialCopyWhat;
    private User userCopyWho;
    private long timestampWhen;

    @Override
    public int compareTo(Billing billing) {
        return Long.compare(timestampWhen, billing.timestampWhen);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Billing that) && this.timestampWhen == that.timestampWhen;
    }
}
