package com.andrea.librarymanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    @ManyToOne
    private Book book;

    private LocalDate loanDate;

    private LocalDate expectedReturnDate;

    private LocalDate actualReturnDate;
}