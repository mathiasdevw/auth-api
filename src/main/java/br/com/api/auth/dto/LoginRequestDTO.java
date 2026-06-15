package br.com.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(

        @NotBlank
        String email,

        @NotBlank
        @Size(min = 8)
        String password
) {
}
