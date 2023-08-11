package dev.misei.domain.payload;

public record MaterialPayload(
        String id,
        String name,
        String description,
        String retailerPrice,
        String ourPrice,
        boolean generic,
        int quantity,
        String base64Image
) {
    @Override
    public boolean equals(Object o) {
        return (o instanceof MaterialPayload that) && this.id.equalsIgnoreCase(that.id);
    }
}

