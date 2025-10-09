package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.infrastructure.persistence.BookEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaBookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByTitle(String Title);

    long count();

    List<BookEntity> findByGenre_Name(String GenreName);

    List<BookEntity> findByAuthor_FirstName(String Author);

    @Query("SELECT b FROM BookEntity b WHERE FUNCTION('year', b.releaseDate) = :year")
    List<BookEntity> findByReleaseDate(int Year);

    List<BookEntity> findByTitleContainingIgnoreCase(String Title);

    List<BookEntity> findAllByOrderByPriceAsc();

    @Modifying
    @Transactional
    @Query("UPDATE BookEntity b SET b.price = :price WHERE b.id = :id")
    BookEntity updatePrice(long BookId, double Price);

    List<BookEntity> findAllByOrderByAverageRatingDesc();
}
