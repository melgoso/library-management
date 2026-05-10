package com.andrea.librarymanagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BookRequestDTO(

        @NotBlank(message="Title is required")
        String title,

        @NotBlank(message="ISBN is required")
        String isbn,

        @Min(value=0, message = "Available copies cannot be negative")
        int availableCopies
) {
}