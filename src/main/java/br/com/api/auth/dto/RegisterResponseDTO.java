package br.com.api.auth.dto;

import java.util.UUID;

public record RegisterResponseDTO(

        String message,
        UUID id,
        String name,
        String email,
        String role

) {
}
