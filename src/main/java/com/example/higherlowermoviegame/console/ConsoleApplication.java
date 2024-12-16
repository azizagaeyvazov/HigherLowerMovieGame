package com.example.higherlowermoviegame.console;

import com.example.higherlowermoviegame.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@AllArgsConstructor
@SpringBootApplication(scanBasePackages = "com.example.higherlowermoviegame")
public class ConsoleApplication implements CommandLineRunner {
    private final Game game;
    private final MovieService service;

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (service.isDatabaseEmpty()) {
            log.error("App did not start properly. Database is empty!");
            System.exit(0);
        }
        game.start();
    }
}
