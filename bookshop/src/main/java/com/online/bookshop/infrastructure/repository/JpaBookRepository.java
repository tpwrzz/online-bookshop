package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.infrastructure.persistence.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaBookRepository extends JpaRepository<BookEntity, Long> {
    long count();

    List<BookEntity> findByGenre_NameContainingIgnoreCase(String GenreName);

    List<BookEntity> findByAuthor_FirstNameContainingIgnoreCase(String Author);

    @Query("SELECT b FROM BookEntity b WHERE YEAR(b.releaseDate) = :year")
    List<BookEntity> findByReleaseDate(@Param("year") int year);

    List<BookEntity> findByTitleContainingIgnoreCase(String Title);

    List<BookEntity> findAllByOrderByPriceAsc();

    List<BookEntity> findAllByOrderByAverageRatingDesc();
}
