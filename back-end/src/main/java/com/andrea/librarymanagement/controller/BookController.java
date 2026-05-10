package com.andrea.librarymanagement.controller;

import com.andrea.librarymanagement.dto.BookRequestDTO;
import com.andrea.librarymanagement.model.Book;
import com.andrea.librarymanagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createBook(
            @Valid @RequestBody BookRequestDTO dto
    ) {

        return bookService.createBook(dto);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam String title
    ) {

        return bookService.searchBooks(title);
    }
}