package com.example.higherlowermoviegame.dataAccess;

import com.example.higherlowermoviegame.console.Game;
import com.example.higherlowermoviegame.service.MovieService;
import com.example.higherlowermoviegame.entities.Movie;
import com.example.higherlowermoviegame.enums.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MovieRepositoryTest {

    @MockitoBean
    Game game;

    @MockitoBean
    MovieService movieService;

    @Autowired
    MovieRepository movieRepo;


    @BeforeEach
    void setUp() {
        movieRepo.deleteAll();
    }

    @Test
    void findRandomMovie_GivenThereIsMovie_ReturnsMovie() {
        // TODO: we can optimize object creation making it more clean
        // given
        Movie movie = new Movie();
        movie.setOriginalTitle("");
        movie.setTagline("");
        movie.setTitle("");
        movie.setOverview("");
        movie.setPopularity(0.0);
        movie.setRevenue(0.0);
        movie.setRuntime(0.0);
        movie.setVoteAverage(0.0);
        movie.setVoteCount(0L);
        movie.setReleaseDate(LocalDate.now());
        Movie expectedMovie = movieRepo.save(movie);

        // when
        Movie actualMovie = movieRepo.findRandomMovie();

        // then
        assertThat(actualMovie).isEqualTo(expectedMovie);
    }

    @Test
    void findRandomMovie_GivenThereIsNoData_ReturnsNull() {
        //when
        Movie expectedMovie = movieRepo.findRandomMovie();

        //then
        assertThat(expectedMovie).isNull();
    }

    @Test
    void findRandomMovieWhereCategoryNotEqual_GivenMoviesWithDifferentValues_ReturnsMovie() {

        String selectedCategory = Category.RUNTIME.name();

        //given
        Movie movie1 = new Movie();
        movie1.setTitle("Title - 1");
        movie1.setRuntime(40.0);
        movie1.setOriginalTitle("");
        movie1.setTagline("");
        movie1.setOverview("");
        movie1.setPopularity(0.0);
        movie1.setRevenue(0.0);
        movie1.setVoteAverage(0.0);
        movie1.setVoteCount(0L);
        movie1.setReleaseDate(LocalDate.now());
        movieRepo.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Title - 2");
        movie2.setRuntime(20.0);
        movie2.setOriginalTitle("");
        movie2.setTagline("");
        movie2.setOverview("");
        movie2.setPopularity(0.0);
        movie2.setRevenue(0.0);
        movie2.setVoteAverage(0.0);
        movie2.setVoteCount(0L);
        movie2.setReleaseDate(LocalDate.now());
        Movie expectedMovie = movieRepo.save(movie2);

        //when
        Movie actualMovie = movieRepo.findRandomMovieWhereCategoryNotEqual(selectedCategory, movie1.getRuntime());

        //then
        assertThat(actualMovie).isEqualTo(expectedMovie);
    }

    @Test
    void findRandomMovieWhereCategoryNotEqual_GivenMoviesWithSameValue_ReturnsNull() {

        String selectedCategory = Category.POPULARITY.name();

        //given
        Movie movie1 = new Movie();
        movie1.setPopularity(5.0);
        movie1.setTitle("Title - 1");
        movie1.setRuntime(0.0);
        movie1.setOriginalTitle("");
        movie1.setTagline("");
        movie1.setOverview("");
        movie1.setRevenue(0.0);
        movie1.setVoteAverage(0.0);
        movie1.setVoteCount(0L);
        movie1.setReleaseDate(LocalDate.now());
        movieRepo.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Title - 2");
        movie2.setRuntime(0.0);
        movie2.setOriginalTitle("");
        movie2.setTagline("");
        movie2.setOverview("");
        movie2.setPopularity(5.0);
        movie2.setRevenue(0.0);
        movie2.setVoteAverage(0.0);
        movie2.setVoteCount(0L);
        movie2.setReleaseDate(LocalDate.now());
        movieRepo.save(movie2);

        //when
        Movie actualMovie = movieRepo.findRandomMovieWhereCategoryNotEqual(selectedCategory, movie1.getPopularity());

        //then
        assertThat(actualMovie).isNull();
    }


    @Test
    void findRandomMovieWhereCategoryNotEqualAndInRange_GivenThereIsMovie_ReturnsMovieWithDifferentValueAndInDefinedRange() {
        //given
        String selectedCategory = Category.POPULARITY.name();
        Double movie1CategoryValue = 8.0;
        Double difficultyRange = 0.2;
        Double lowerBound = movie1CategoryValue - difficultyRange;
        Double upperBound = movie1CategoryValue + difficultyRange;

        Movie movie1 = new Movie();
        movie1.setPopularity(8.0);
        movie1.setTitle("");
        movie1.setRuntime(0.0);
        movie1.setOriginalTitle("");
        movie1.setTagline("");
        movie1.setOverview("");
        movie1.setRevenue(0.0);
        movie1.setVoteAverage(0.0);
        movie1.setVoteCount(0L);
        movie1.setReleaseDate(LocalDate.now());
        movieRepo.save(movie1);

        Movie movie2 = new Movie();
        movie2.setPopularity(8.1);
        movie2.setTitle("");
        movie2.setOverview("");
        movie2.setRevenue(0.0);
        movie2.setRuntime(0.0);
        movie2.setTagline("");
        movie2.setOriginalTitle("");
        movie2.setVoteCount(0L);
        movie2.setVoteAverage(0.0);
        movie2.setReleaseDate(LocalDate.now());
        Movie expectedMovie = movieRepo.save(movie2);

        //when
        Movie actualMovie = movieRepo.findRandomMovieWhereCategoryNotEqualAndInRange(selectedCategory,
                movie1CategoryValue, lowerBound, upperBound);

        //then
        assertThat(actualMovie).isEqualTo(expectedMovie);
    }

    @Test
    void findRandomMovieWhereCategoryNotEqualAndInRange_GivenNoMovieWithinGivenRange_ReturnsNull() {

        String selectedCategory = Category.REVENUE.name();
        Double lowerBound = 5.0;
        Double upperBound = 5.4;

        //given

        Movie movie1 = new Movie();
        movie1.setRevenue(5.2);
        movie1.setVoteAverage(0.0);
        movie1.setPopularity(0.0);
        movie1.setTitle("");
        movie1.setRuntime(0.0);
        movie1.setOriginalTitle("");
        movie1.setTagline("");
        movie1.setOverview("");
        movie1.setVoteCount(0L);
        movie1.setReleaseDate(LocalDate.now());
        movieRepo.save(movie1);

        Movie movie2 = new Movie();
        movie2.setRevenue(9.0);
        movie2.setVoteAverage(0.0);
        movie2.setPopularity(0.0);
        movie2.setTitle("");
        movie2.setOverview("");
        movie2.setRuntime(0.0);
        movie2.setTagline("");
        movie2.setOriginalTitle("");
        movie2.setVoteCount(0L);
        movie2.setReleaseDate(LocalDate.now());
        movieRepo.save(movie2);

        //when
        Movie actualMovie = movieRepo.findRandomMovieWhereCategoryNotEqualAndInRange(selectedCategory,
                movie1.getRevenue(), lowerBound, upperBound);

        //then
        assertThat(actualMovie).isNull();
    }
}