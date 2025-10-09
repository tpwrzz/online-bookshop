package com.online.bookshop.api.controller;

import com.online.bookshop.application.service.BookService;
import com.online.bookshop.domain.model.Book;
import com.online.bookshop.domain.model.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/searchByTitle")
    public ResponseEntity<List<Book>> findByTitle(@RequestParam String title) {
        return ResponseEntity.ok(service.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book saved = service.save(book);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        Book updated = service.save(book);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countBooks() {
        return ResponseEntity.ok(service.bookCount());
    }

    @GetMapping("/byGenre")
    public ResponseEntity<List<Book>> findByGenre(@RequestParam String genreName) {
        return ResponseEntity.ok(service.findByGenre(genreName));
    }

    @GetMapping("/byAuthor")
    public ResponseEntity<List<Book>> findByAuthor(@RequestParam String author) {
        return ResponseEntity.ok(service.findByAuthor(author));
    }

    @GetMapping("/byYear")
    public ResponseEntity<List<Book>> findByYear(@RequestParam int year) {
        return ResponseEntity.ok(service.findByYear(year));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> findByTitleContaining(@RequestParam String title) {
        return ResponseEntity.ok(service.findByTitleContaining(title));
    }

    @GetMapping("/sortedByPrice")
    public ResponseEntity<List<Book>> sortByPrice() {
        return ResponseEntity.ok(service.sortByPrice());
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Book> updatePrice(
            @PathVariable("id") Long bookId,
            @RequestParam double price
    ) {
        Book updated = service.updatePrice(bookId, price);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReviewsByBookId(id));
    }

    @GetMapping("/{id}/averageRating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long id) {
        return service.getAverageRatingByBookId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sortedByAverageRating")
    public ResponseEntity<List<Book>> getBooksSortedByAverageRating() {
        return ResponseEntity.ok(service.getBooksSortedByAverageRating());
    }

    @GetMapping("/{id}/reviewsByRating")
    public ResponseEntity<List<Review>> getReviewsByRating(
            @PathVariable Long id,
            @RequestParam double rating) {
        return ResponseEntity.ok(service.getReviewsByBookIdAndRating(id, rating));
    }

}
