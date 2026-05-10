package com.andrea.librarymanagement.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LoanRequestDTO(

        @NotNull(message = "Book id is required to loan a book")
        Long bookId,

        @NotNull(message = "User name is required to loan a book")
        String userName,

        @NotNull
        @Future(message = "Expected return date must be a future date")
        LocalDate expectedReturnDate
) {
}