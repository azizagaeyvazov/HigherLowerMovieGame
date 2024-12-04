package com.example.higherlowermoviegame;

import com.example.higherlowermoviegame.console.Game;
import com.example.higherlowermoviegame.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class HigherLowerMovieGameApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HigherLowerMovieGameApplication.class, args);
    }

    private final Game game;

    private final MovieService service;

    @Override
    public void run(String... args) {
        if (service.isDatabaseEmpty()) {
            log.error("App did not start properly. Database is empty!");
            System.exit(0);
        }
        game.start();
    }
}
