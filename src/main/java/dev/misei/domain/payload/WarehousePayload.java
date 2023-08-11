package dev.misei.domain.payload;

import java.util.Set;

public record WarehousePayload(
        String name,
        boolean generic,
        Set<StandPayload> stands
) {
}

