package com.example.higherlowermoviegame.service;

import org.springframework.stereotype.Service;

@Service
public interface HighestScoreService {

    int getHighestScore(String selectedMode);

    void updateHighestScore(String selectedMode, int score);
}
