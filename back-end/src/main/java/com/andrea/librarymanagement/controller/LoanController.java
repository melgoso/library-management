package com.andrea.librarymanagement.controller;

import com.andrea.librarymanagement.dto.LoanRequestDTO;
import com.andrea.librarymanagement.model.Loan;
import com.andrea.librarymanagement.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin("*")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan createLoan(
            @Valid @RequestBody LoanRequestDTO dto
    ) {

        return loanService.createLoan(dto);
    }

    @PutMapping("/{loanId}/return")
    public Loan returnBook(
            @PathVariable Long loanId
    ) {

        return loanService.returnBook(loanId);
    }

    @GetMapping("/user/{userName}")
    public List<Loan> getActiveLoans(
            @PathVariable String userName
    ) {

        return loanService.getActiveLoans(userName);
    }
}