package com.andrea.librarymanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BookRequestDTO(

        @NotBlank
        String title,

        @NotBlank
        String isbn,

        @Min(0)
        int availableCopies
) {
}