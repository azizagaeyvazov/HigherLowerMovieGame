package com.example.higherlowermoviegame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewGameResponse {

    MovieResponse movie1;
    MovieResponse movie2;

    int highestScore;
}
