package dev.misei.domain.payload;

public record BillingPayload(
        String id,
        WarehousePayload warehouseCopyWhere,
        MaterialPayload materialCopyWhat,
        UserPayload userCopyWho,
        long timestampWhen
) implements Comparable<BillingPayload> {

    @Override
    public int compareTo(BillingPayload billingPayload) {
        return Long.compare(timestampWhen, billingPayload.timestampWhen);
    }
}

