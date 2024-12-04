package com.example.higherlowermoviegame.service;

public interface HighestScoreService {

    int getHighestScore(String selectedMode);

    void updateHighestScore(String selectedMode, int score);
}
