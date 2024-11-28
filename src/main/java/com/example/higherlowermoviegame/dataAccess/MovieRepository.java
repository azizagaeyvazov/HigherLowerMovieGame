package com.example.higherlowermoviegame.dataAccess;

import com.example.higherlowermoviegame.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    String CASE_WHEN = "\nCASE\n"
            + "    WHEN :category = 'VOTE_AVERAGE' THEN VOTE_AVERAGE\n"
            + "    WHEN :category = 'POPULARITY' THEN POPULARITY\n"
            + "    WHEN :category = 'RUNTIME' THEN RUNTIME\n"
            + "    WHEN :category = 'REVENUE' THEN REVENUE\n"
            + "    ELSE 0\n"
            + "END\n";

    @Query(value = "SELECT * FROM Movie ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Movie findRandomMovie();

    @Query(value = "SELECT * FROM Movie WHERE" + CASE_WHEN + "<> :movie1CategoryValue ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Movie findRandomMovieWhereCategoryNotEqual(String category, Double movie1CategoryValue);

    @Query(value = "SELECT * FROM Movie WHERE(" + CASE_WHEN + " BETWEEN :lowerBound AND :upperBound) AND (" + CASE_WHEN + " <> :movie1CategoryValue) ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Movie findRandomMovieWhereCategoryNotEqualAndInRange(String category, Double movie1CategoryValue, Double lowerBound, Double upperBound);
}
