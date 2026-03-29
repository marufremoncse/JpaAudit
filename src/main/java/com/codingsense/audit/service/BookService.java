package com.codingsense.audit.service;

import com.codingsense.audit.dao.BookRepository;
import com.codingsense.audit.dto.BookRequest;
import com.codingsense.audit.model.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public String saveBook(BookRequest<Book> bookRequest) {
        log.info("Timezone {}", Calendar.getInstance().getTimeZone());
        Book book = bookRequest.getBook();
        book.setCreatedBy(bookRequest.getLoggedInUser());
        bookRepository.save(book);
        return "Book saved successfully";
    }

    public String updateBook(int bookId, BookRequest<Book> bookRequest) {
        bookRepository.findById(bookId).ifPresentOrElse(book -> {
            book.setPrice(bookRequest.getBook().getPrice());
            book.setUpdatedBy(bookRequest.getLoggedInUser());
            bookRepository.save(book);
        }, () -> {
            log.info("Book with id {} not found", bookId);
            throw new RuntimeException("Book with id " + bookId + " not found");
        });
        return "Book updated successfully";
    }
}
