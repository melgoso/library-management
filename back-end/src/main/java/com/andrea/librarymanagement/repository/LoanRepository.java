package com.andrea.librarymanagement.repository;

import com.andrea.librarymanagement.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserNameAndActualReturnDateIsNull(String userName);
}