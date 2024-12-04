package com.example.higherlowermoviegame.service;

import com.example.higherlowermoviegame.dataAccess.MovieRepository;
import com.example.higherlowermoviegame.dto.MovieResponse;
import com.example.higherlowermoviegame.dto.NewGameResponse;
import com.example.higherlowermoviegame.entities.Movie;
import com.example.higherlowermoviegame.enums.Mode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieManager implements MovieService {

    ModelMapper mapper = new ModelMapper();

    private final MovieRepository movieRepository;

    private final HighestScoreService highestScoreService;

    public NewGameResponse startNewGame(String category, String selectedMode) {

        var movie1 = movieRepository.findRandomMovie();
        var movieResponse1 = mapper.map(movie1, MovieResponse.class);

        Double movie1CategoryValue = movieResponse1.getValueByCategory(category);
        Movie movie2 = movieRepository.findRandomMovieWhereCategoryNotEqual(category, movie1CategoryValue);
        MovieResponse movieResponse2 = mapper.map(movie2, MovieResponse.class);
        int highestScore = highestScoreService.getHighestScore(selectedMode);

        return new NewGameResponse(movieResponse1, movieResponse2, highestScore);
    }

    @Override
    public MovieResponse getNextMovie(String selectedMode, String selectedCategory, Double movie1CategoryValue, int score) {
        Movie movie2;

        if (isHardRound(selectedMode, score)) {
            Double lowerBound = movie1CategoryValue - 0.2;
            Double upperBound = movie1CategoryValue + 0.2;

            movie2 = movieRepository.findRandomMovieWhereCategoryNotEqualAndInRange(selectedCategory, movie1CategoryValue, lowerBound, upperBound);
            if (movie2 == null){
                movie2 = movieRepository.findRandomMovieWhereCategoryNotEqual(selectedCategory, movie1CategoryValue);
            }
        } else {
            movie2 = movieRepository.findRandomMovieWhereCategoryNotEqual(selectedCategory, movie1CategoryValue);
        }
        return mapper.map(movie2, MovieResponse.class);
    }

    @Override
    public boolean isDatabaseEmpty() {
        if (movieRepository.count() == 0) {
            log.info("No movies found");
            return true;
        }
        return false;
    }

    boolean isHardRound(String selectedMode, int score) {
        return score % 2 == 1 && selectedMode.equalsIgnoreCase(Mode.HARD.name());
    }

}

