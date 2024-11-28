package com.example.higherlowermoviegame.service;

import com.example.higherlowermoviegame.Game;
import com.example.higherlowermoviegame.dataAccess.HighestScoreRepository;
import com.example.higherlowermoviegame.entities.HighestScoreEntity;
import com.example.higherlowermoviegame.enums.Mode;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class HighestScoreManagerTest {

    @MockBean
    private Game game;

    @MockBean
    private MovieService movieService;

    @Mock
    private HighestScoreRepository highestScoreRepository;
    @InjectMocks
    private HighestScoreManager highestScoreManager;

    @Test
    void getHighestScore_GivenHardMode_ReturnsHighestScore() {

        String selectedMode = Mode.HARD.name();

        HighestScoreEntity highestScoreEntity = new HighestScoreEntity(null, 1, 0);

        when(highestScoreRepository.findFirstBy()).thenReturn(Optional.of(highestScoreEntity));

        int expectedValue = highestScoreEntity.getHardMode();

        assertTrue(highestScoreManager.isHardMode(selectedMode));

        int actualValue = highestScoreManager.getHighestScore(selectedMode);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getHighestScore_GivenNormalMode_ReturnsHighestScore() {

        String selectedMode = Mode.NORMAL.name();

        HighestScoreEntity highestScoreEntity = new HighestScoreEntity(null, 1, 0);

        when(highestScoreRepository.findFirstBy()).thenReturn(Optional.of(highestScoreEntity));

        int expectedValue = highestScoreEntity.getNormalMode();

        assertFalse(highestScoreManager.isHardMode(selectedMode));

        int actualValue = highestScoreManager.getHighestScore(selectedMode);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void isHardMode_ReturnsTrue() {
        String selectedMode = "HARD";

        assertTrue(highestScoreManager.isHardMode(selectedMode));
    }

    @Test
    void isHardMode_ReturnsFalse() {
        String selectedMode = Mode.NORMAL.name();

        assertFalse(highestScoreManager.isHardMode(selectedMode));
    }
}
