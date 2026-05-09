package com.andrea.librarymanagement.service;

import com.andrea.librarymanagement.dto.BookRequestDTO;
import com.andrea.librarymanagement.exception.DuplicateIsbnException;
import com.andrea.librarymanagement.model.Book;
import com.andrea.librarymanagement.repository.BookRepository;
import com.andrea.librarymanagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookRequestDTO dto) {

        if (bookRepository.existsByIsbn(dto.isbn())) {
            throw new DuplicateIsbnException(
                    "A book with this ISBN already exists"
            );
        }

        Book book = new Book();

        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setAvailableCopies(dto.availableCopies());

        return bookRepository.save(book);
    }

    public List<Book> searchBooks(String title) {

        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Book getBookById(Long id) {

        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));
    }
}