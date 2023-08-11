package dev.misei.domain.payload;

import java.util.Set;

public record StandPayload(
        String id,
        Set<MaterialPayload> materials
) {
}

