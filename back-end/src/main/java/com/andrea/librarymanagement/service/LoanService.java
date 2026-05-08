package com.andrea.librarymanagement.service;

import com.andrea.librarymanagement.dto.LoanRequestDTO;
import com.andrea.librarymanagement.exception.NoAvailableCopiesException;
import com.andrea.librarymanagement.exception.ResourceNotFoundException;
import com.andrea.librarymanagement.model.Book;
import com.andrea.librarymanagement.model.Loan;
import com.andrea.librarymanagement.repository.BookRepository;
import com.andrea.librarymanagement.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanService(
            LoanRepository loanRepository,
            BookRepository bookRepository
    ) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public Loan createLoan(LoanRequestDTO dto) {

        Book book = bookRepository.findById(dto.bookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new NoAvailableCopiesException(
                    "No available copies for this book");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        Loan loan = new Loan();

        loan.setBook(book);
        loan.setUserName(dto.userName());
        loan.setLoanDate(LocalDate.now());
        loan.setExpectedReturnDate(dto.expectedReturnDate());

        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loan not found"));

        loan.setActualReturnDate(LocalDate.now());

        Book book = loan.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public List<Loan> getActiveLoans(String userName) {

        return loanRepository
                .findByUserNameAndActualReturnDateIsNull(userName);
    }
}