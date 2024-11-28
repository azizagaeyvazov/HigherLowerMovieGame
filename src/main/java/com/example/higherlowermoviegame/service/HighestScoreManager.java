package com.example.higherlowermoviegame.service;

import com.example.higherlowermoviegame.dataAccess.HighestScoreRepository;
import com.example.higherlowermoviegame.entities.HighestScoreEntity;
import com.example.higherlowermoviegame.enums.Mode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class HighestScoreManager implements HighestScoreService {
    private final HighestScoreRepository highestScoreRepository;

    @Override
    public int getHighestScore(String selectedMode) {
        Optional<HighestScoreEntity> highestScoreEntityOptional = highestScoreRepository.findFirstBy();
        HighestScoreEntity highestScoreEntity = highestScoreEntityOptional.orElse(
                new HighestScoreEntity(null, 0, 0)
        );

        if (isHardMode(selectedMode)) {
            return highestScoreEntity.getHardMode();
        } else {
            return highestScoreEntity.getNormalMode();
        }
    }

    @Override
    public void updateHighestScore(String selectedMode, int score) {
        Optional<HighestScoreEntity> highestScoreEntityOptional = highestScoreRepository.findFirstBy();
        HighestScoreEntity highestScoreEntity = highestScoreEntityOptional.orElse(
                new HighestScoreEntity(1L, 0, 0)
        );

        if (isHardMode(selectedMode)) {
            highestScoreEntity.setHardMode(score);
        } else {
            highestScoreEntity.setNormalMode(score);
        }
        highestScoreRepository.save(highestScoreEntity);
    }

    boolean isHardMode(String selectedMode) {
        return selectedMode.equalsIgnoreCase(Mode.HARD.name());
    }
}
