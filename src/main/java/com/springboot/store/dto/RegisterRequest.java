package com.springboot.store.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank String username,
    @NotBlank @Size(min = 6, message = "Password must be at least 6 characters") String password,
    @NotBlank @Pattern(regexp = "^(ADMIN|ENGINEER|MANAGER)$", message = "Role must be ADMIN, ENGINEER, or MANAGER") String role
) {}
