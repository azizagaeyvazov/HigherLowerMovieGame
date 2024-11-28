package com.example.higherlowermoviegame.service;

import com.example.higherlowermoviegame.dataAccess.MovieRepository;
import com.example.higherlowermoviegame.dto.MovieResponse;
import com.example.higherlowermoviegame.dto.NewGameResponse;
import com.example.higherlowermoviegame.entities.Movie;
import com.example.higherlowermoviegame.enums.Category;
import com.example.higherlowermoviegame.enums.Mode;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieManagerTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private HighestScoreService highestScoreService;
    @InjectMocks
    private MovieManager movieManager;

    @Test
    void startNewGame() {
        // given
        String category = Category.POPULARITY.name();
        String selectedMode = Mode.NORMAL.name();
        int highestScore = 10;

        Movie movie1 = new Movie();
        movie1.setPopularity(22.0);
        MovieResponse m1_response = new MovieResponse();
        m1_response.setPopularity(movie1.getPopularity());

        Movie movie2 = new Movie();
        movie2.setPopularity(24.0);
        MovieResponse movie2_response = new MovieResponse();
        movie2_response.setPopularity(movie2.getPopularity());

        when(movieRepository.findRandomMovie()).thenReturn(movie1);
        when(movieRepository.findRandomMovieWhereCategoryNotEqual(category, movie1.getPopularity())).thenReturn(movie2);
        when(highestScoreService.getHighestScore(selectedMode)).thenReturn(highestScore);
        NewGameResponse expectedNewGameResponse = new NewGameResponse(
                m1_response,
                movie2_response,
                highestScore);

        // when
        NewGameResponse actualNewGameResponse = movieManager.startNewGame(category, selectedMode);

        // then
        assertEquals(expectedNewGameResponse, actualNewGameResponse);
    }


    @Test
    void getNextMovie_GivenHardRound_ReturnsMovieInGivenRange() {

        String selectedMode = Mode.HARD.name();
        String selectedCategory = Category.VOTE_AVERAGE.name();
        int score = 3;

        //given
        Movie m1 = new Movie();
        m1.setVoteAverage(8.0);
        Double lowerBound = m1.getVoteAverage() - 0.2;
        Double upperBound = m1.getVoteAverage() + 0.2;

        Movie m2 = new Movie();
        m2.setVoteAverage(8.1);

        when(movieRepository.findRandomMovieWhereCategoryNotEqualAndInRange(selectedCategory, m1.getVoteAverage(), lowerBound, upperBound)).thenReturn(m2);

        MovieResponse expectedMovie = new MovieResponse();
        expectedMovie.setVoteAverage(m2.getVoteAverage());

        //when
        MovieResponse actualMovie = movieManager.getNextMovie(selectedMode, selectedCategory, m1.getVoteAverage(), score);

        //then
        assertEquals(expectedMovie, actualMovie);
    }


    @Test
    void getNextMovie_GivenHardRoundAndNoMovieInRange_ReturnsRandomMovie() {

        String selectedMode = Mode.HARD.name();
        String selectedCategory = Category.VOTE_AVERAGE.name();
        int score = 3;

        //given
        Movie movie1 = new Movie();
        movie1.setVoteAverage(8.0);
        Double lowerBound = movie1.getVoteAverage() - 0.2;
        Double upperBound = movie1.getVoteAverage() + 0.2;

        Movie movie2 = new Movie();
        movie2.setVoteAverage(10.0);

        when(movieRepository.findRandomMovieWhereCategoryNotEqualAndInRange(selectedCategory, movie1.getVoteAverage(), lowerBound, upperBound)).thenReturn(null);
        when(movieRepository.findRandomMovieWhereCategoryNotEqual(selectedCategory, movie1.getVoteAverage())).thenReturn(movie2);

        MovieResponse expectedMovie = new MovieResponse();
        expectedMovie.setVoteAverage(movie2.getVoteAverage());

        //when
        MovieResponse actualMovie = movieManager.getNextMovie(selectedMode, selectedCategory, movie1.getVoteAverage(), score);

        //then
        assertEquals(expectedMovie, actualMovie);
    }

    @Test
    void getNextMovie_GivenNormalMode_ReturnsRandomMovie() {

        String selectedMode = Mode.NORMAL.name();
        String selectedCategory = Category.VOTE_AVERAGE.name();
        int score = 1;

        //given
        Movie movie1 = new Movie();
        movie1.setVoteAverage(8.0);
        Double lowerBound = movie1.getVoteAverage() - 0.2;
        Double upperBound = movie1.getVoteAverage() + 0.2;

        Movie movie2 = new Movie();
        movie2.setVoteAverage(10.0);

        when(movieRepository.findRandomMovieWhereCategoryNotEqual(selectedCategory, movie1.getVoteAverage())).thenReturn(movie2);

        MovieResponse expectedMovie = new MovieResponse();
        expectedMovie.setVoteAverage(movie2.getVoteAverage());

        //when
        MovieResponse actualMovie = movieManager.getNextMovie(selectedMode, selectedCategory, movie1.getVoteAverage(), score);

        //then
        assertEquals(expectedMovie, actualMovie);
    }

    @Test
    @Disabled
    void getNextMovie_GivenNoData_ThrowsException() {
        String selectedCategory = Category.POPULARITY.name();
        Double lowerBound = 5.0;
        Double upperBound = 5.4;

        Movie movie1 = new Movie();
        movie1.setPopularity(0.0);

        when(movieRepository.findRandomMovieWhereCategoryNotEqualAndInRange(selectedCategory, movie1.getPopularity(), lowerBound, upperBound)).thenReturn(null);
        when(movieRepository.findRandomMovieWhereCategoryNotEqual(selectedCategory, movie1.getPopularity())).thenReturn(null);

        //throws exception
    }

    @Test
    void isDatabaseEmpty_GivenNoData_ReturnsTrue() {

        given(movieRepository.count()).willReturn(0L);

        assertTrue(movieManager.isDatabaseEmpty());
    }

    @Test
    void isDatabaseEmpty_GivenData_ReturnsFalse() {

        given(movieRepository.count()).willReturn(1L);

        assertFalse(movieManager.isDatabaseEmpty());
    }

    @Test
    void isHardRound_ReturnsTrue() {

        String selectedMode = Mode.HARD.name();
        int score = 3;

        assertTrue(movieManager.isHardRound(selectedMode, score));
    }

    @Test
    void isHardRound_ReturnsFalse() {

        String selectedMode = Mode.NORMAL.name();
        int score = 3;

        assertFalse(movieManager.isHardRound(selectedMode, score));
    }

}