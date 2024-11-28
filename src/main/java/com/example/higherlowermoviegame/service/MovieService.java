package com.example.higherlowermoviegame.service;

import com.example.higherlowermoviegame.dto.MovieResponse;
import com.example.higherlowermoviegame.dto.NewGameResponse;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    boolean isDatabaseEmpty();

    NewGameResponse startNewGame(String selectedCategory, String selectedMode);

    MovieResponse getNextMovie(String selectedMode, String selectedCategory, Double movie1CategoryValue, int score);

}
