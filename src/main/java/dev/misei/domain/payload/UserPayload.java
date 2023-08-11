package dev.misei.domain.payload;

public record UserPayload(
        String userName,
        String fullName,
        String password,
        String base64Image,
        String phone,
        String userRole
) {
}



