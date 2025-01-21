package com.alura.ForoHubAPI.dto.user;

import jakarta.validation.constraints.Email;

public record UpdateUserDTO(Long userId, @Email String email, String name, String password) {

}
