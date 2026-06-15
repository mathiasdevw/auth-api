package br.com.api.auth.dto;

import java.util.UUID;

public record RegisterResponseDTO(

        String message,
        String name,
        String email,
        String role

) {
}
