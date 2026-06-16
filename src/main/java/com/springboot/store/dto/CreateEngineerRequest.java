package com.springboot.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEngineerRequest(
    @NotBlank String name,
    @NotBlank String techStack,
    @NotNull Integer projectId
) {}