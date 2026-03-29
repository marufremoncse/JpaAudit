package com.codingsense.audit.controller;

import com.codingsense.audit.dto.BookRequest;
import com.codingsense.audit.model.Book;
import com.codingsense.audit.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public String saveBook(@RequestBody BookRequest<Book> bookRequest) {
        return bookService.saveBook(bookRequest);
    }

    @PutMapping("/{bookId}")
    public String updateBook(
            @PathVariable int bookId,
            @RequestBody BookRequest<Book> bookRequest) {
        return bookService.updateBook(bookId, bookRequest);
    }
}
