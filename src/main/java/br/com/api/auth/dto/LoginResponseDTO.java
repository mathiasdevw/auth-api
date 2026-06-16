package br.com.api.auth.dto;

import br.com.api.auth.entity.Role;

import java.util.UUID;

public record LoginResponseDTO(
        String token,
        String type,
        UUID userId,
        String role
) {}