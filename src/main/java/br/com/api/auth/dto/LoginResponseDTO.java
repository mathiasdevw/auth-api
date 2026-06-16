package br.com.api.auth.dto;

import br.com.api.auth.entity.Role;

public record LoginResponseDTO(
        String message,
        String name,
        String email,
        String role
) {
}
