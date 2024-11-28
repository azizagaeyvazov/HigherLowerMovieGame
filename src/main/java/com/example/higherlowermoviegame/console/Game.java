package com.example.higherlowermoviegame.console;

import com.example.higherlowermoviegame.dto.MovieResponse;
import com.example.higherlowermoviegame.dto.NewGameResponse;
import com.example.higherlowermoviegame.enums.Category;
import com.example.higherlowermoviegame.enums.Compare;
import com.example.higherlowermoviegame.enums.Mode;
import com.example.higherlowermoviegame.service.HighestScoreService;
import com.example.higherlowermoviegame.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
@AllArgsConstructor
public class Game {
    private final Scanner sc = new Scanner(System.in);
    private final ModelMapper mapper = new ModelMapper();
    private final MovieService movieService;
    private final HighestScoreService highestScoreService;

    public void start() {

        Category selectedCategory = getSelectedCategory();
        Mode selectedMode = getSelectedMode();

        getStartKeyWord();
        int score = 0;

        NewGameResponse newGameResponse = movieService.startNewGame(selectedCategory, selectedMode);
        MovieResponse movie1 = newGameResponse.getMovie1();
        MovieResponse movie2 = newGameResponse.getMovie2();

        Double movie1CategoryValue = movie1.getValueByCategory(selectedCategory);
        Double movie2CategoryValue = movie2.getValueByCategory(selectedCategory);
        int highestScore = newGameResponse.getHighestScore();

        displayMovies(selectedCategory, movie1, movie2, score, highestScore);
        boolean userIsRight = checkUserAnswer(movie1CategoryValue, movie2CategoryValue);

        while (userIsRight) {
            ++score;
            log.info("correct answer");
            mapper.map(movie2, movie1);
            movie2 = movieService.getNextMovie(selectedMode, selectedCategory, movie2CategoryValue, score);
            movie1CategoryValue = movie1.getValueByCategory(selectedCategory);
            movie2CategoryValue = movie2.getValueByCategory(selectedCategory);

            displayMovies(selectedCategory, movie1, movie2, score, highestScore);
            userIsRight = checkUserAnswer(movie1CategoryValue, movie2CategoryValue);
        }
        log.info("Wrong answer. Game is over!" +
                "Your score: {}", score);
        if (highestScore < score) {
            highestScoreService.updateHighestScore(selectedMode, score);
        }
        start();
    }

    String getUserInput() {
        return sc.nextLine();
    }
    void displayMovies(Category selectedCategory, MovieResponse movie1, MovieResponse movie2, int score, int highestScore) {
        log.info("current score: {} " +
                "\nHighest score: {}", score, highestScore);
        log.info("Compare movies according to their {}", selectedCategory);
        log.info("Type {} or {} instead of dots: ", Compare.HIGHER.name(), Compare.LOWER.name());
        log.info("{} of {} is {}", selectedCategory, movie1.getOriginalTitle(), movie1.getValueByCategory(selectedCategory));
        log.info("{} has ... {}", movie2.getOriginalTitle(), selectedCategory);
    }

    Category getSelectedCategory() {

        log.info("Choose category you want to compare: \n{} \n{} \n{} \n{}"
                , Category.VOTE_AVERAGE, Category.POPULARITY, Category.RUNTIME, Category.REVENUE);

        try {
            return Category.valueOf(getUserInput().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.info("Invalid input.");
            return getSelectedCategory();
        }
    }

    Mode getSelectedMode() {

        log.info("Choose mode: \n{} \n{}", Mode.NORMAL, Mode.HARD);

        try {
            return Mode.valueOf(getUserInput().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.info("Invalid input.");
            return getSelectedMode();
        }
    }

    void getStartKeyWord() {
        log.info("Type 'start' to start a new game:");
        if (!getUserInput().equalsIgnoreCase("start")) {
            log.warn("Wrong keyword.");
            getStartKeyWord();
        } else {
            log.info("Game started. Good lucks!");
        }
    }

    boolean checkUserAnswer(Double movie1CategoryValue, Double movie2CategoryValue) {
        Compare expectedAnswer = null;
        try {
            expectedAnswer = Compare.valueOf(getUserInput().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.info("Invalid input.");
            checkUserAnswer(movie1CategoryValue, movie2CategoryValue);
        }
        Compare actualAnswer = movie1CategoryValue < movie2CategoryValue ? Compare.HIGHER : Compare.LOWER;
        return expectedAnswer == actualAnswer;
    }
}

//1. Refactor to use Enum (Category, Mode) type where it is necessary

//2. Start writing APIs in controller that will work with Service

//3. Check how to add method to enum to compare and return boolean, i.e.;
//   Mode mode = Mode.NORMAL;
//   mode.isHardMode();