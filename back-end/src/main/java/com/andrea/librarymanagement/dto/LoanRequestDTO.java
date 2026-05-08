package com.andrea.librarymanagement.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LoanRequestDTO(

        @NotNull
        Long bookId,

        @NotNull
        String userName,

        @NotNull
        @Future
        LocalDate expectedReturnDate
) {
}