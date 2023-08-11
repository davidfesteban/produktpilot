package dev.misei.domain.payload;

import java.util.Set;

public record OrganizationPayload(
        String name,
        String licenseCode,
        Set<UserPayload> users,
        Set<WarehousePayload> warehouses,
        Set<BillingPayload> billings
) {
}

