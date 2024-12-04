package com.example.higherlowermoviegame.web.controller;

import com.example.higherlowermoviegame.dto.MovieResponse;
import com.example.higherlowermoviegame.dto.NewGameResponse;
import com.example.higherlowermoviegame.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/startNewGame")
    ResponseEntity<NewGameResponse> startNewGame(
            @RequestParam String selectedCategory, @RequestParam String selectedMode) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.startNewGame(selectedCategory, selectedMode));
    }

    @GetMapping("/getNextMovie")
    ResponseEntity<MovieResponse> getNextMovie(@RequestParam String selectedMode, @RequestParam String selectedCategory,
                               @RequestParam Double movie1CategoryValue, @RequestParam int score) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getNextMovie(selectedMode, selectedCategory, movie1CategoryValue, score));
    }
}
