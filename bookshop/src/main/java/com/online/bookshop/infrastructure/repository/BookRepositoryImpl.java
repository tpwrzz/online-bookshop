package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.domain.model.Book;
import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.repository.BookRepository;
import com.online.bookshop.infrastructure.mapper.BookMapper;
import com.online.bookshop.infrastructure.mapper.ReviewMapper;
import com.online.bookshop.infrastructure.persistence.BookEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {

    private final JpaBookRepository jpaRepo;

    @Override
    public List<Book> findAll() {
        return jpaRepo.findAll().stream()
                .map(BookMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jpaRepo.findById(id)
                .map(BookMapper::toDomain);
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = jpaRepo.save(BookMapper.toEntity(book));
        return BookMapper.toDomain(entity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public int bookCount() {
        return (int) jpaRepo.count();
    }

    @Override
    public List<Book> findByGenre(String GenreName) {
        return jpaRepo.findByGenre_NameContainingIgnoreCase(GenreName).stream()
                .map(BookMapper::toDomain)
                .toList();
    }

    @Override
    public List<Book> findByAuthor(String Author) {
        return jpaRepo.findByAuthor_FirstNameContainingIgnoreCase(Author).stream()
                .map(BookMapper::toDomain)
                .toList();
    }

    @Override
    public List<Book> findByYear(int Year) {
        return jpaRepo.findByReleaseDate(Year).stream()
                .map(BookMapper::toDomain)
                .toList();
    }

    @Override
    public List<Book> findByTitleContaining(String Title) {
        return jpaRepo.findByTitleContainingIgnoreCase(Title).stream()
                .map(BookMapper::toDomain)
                .toList();
    }

    @Override
    public List<Book> sortByPrice() {
        return jpaRepo.findAllByOrderByPriceAsc().stream()
                .map(BookMapper::toDomain)
                .toList();
    }

    @Override
    public List<Review> findReviewsByBookId(Long bookId) {
        return jpaRepo.findById(bookId)
                .map(BookEntity::getReviews)
                .orElse(List.of())
                .stream()
                .map(ReviewMapper::toDomain)
                .toList();
    }
    @Override
    public Optional<Double> findAverageRatingByBookId(Long bookId) {
        return jpaRepo.findById(bookId)
                .map(BookEntity::getAverageRating);
    }

    @Override
    public List<Book> findAllSortedByAverageRating() {
        return jpaRepo.findAllByOrderByAverageRatingDesc().stream()
                .map(BookMapper::toDomain)
                .toList();
    }

    @Override
    public List<Review> findReviewsByBookIdAndRating(Long bookId, double rating) {
        return jpaRepo.findById(bookId)
                .map(BookEntity::getReviews)
                .orElse(List.of())
                .stream()
                .filter(r -> r.getReviewRating() == rating)
                .map(ReviewMapper::toDomain)
                .toList();
    }
}
