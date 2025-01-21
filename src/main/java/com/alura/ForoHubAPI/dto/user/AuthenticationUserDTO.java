package com.alura.ForoHubAPI.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationUserDTO(@NotBlank String email, @NotBlank String password) {
    
}
