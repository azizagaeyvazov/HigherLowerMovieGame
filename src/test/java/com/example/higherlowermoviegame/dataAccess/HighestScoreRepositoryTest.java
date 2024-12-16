package com.example.higherlowermoviegame.dataAccess;

import com.example.higherlowermoviegame.console.Game;
import com.example.higherlowermoviegame.service.MovieService;
import com.example.higherlowermoviegame.entities.HighestScoreEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class HighestScoreRepositoryTest {

    @MockitoBean
    Game game;

    @MockitoBean
    MovieService movieService;

    @Autowired
    private HighestScoreRepository highestScoreRepository;

    @Test
    void findFirstBy_GivenMovie_ReturnsMovie() {

        HighestScoreEntity highestScore = new HighestScoreEntity();
        highestScore.setNormalMode(3);
        highestScore.setHardMode(1);
        HighestScoreEntity expected = highestScoreRepository.save(highestScore);

        Optional<HighestScoreEntity> highestScoreEntityOptional = highestScoreRepository.findFirstBy();
        HighestScoreEntity actual = highestScoreEntityOptional.get();

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void findFirstBy_GivenNoData_ReturnsNull() {

        Optional<HighestScoreEntity> highestScoreEntityOptional = highestScoreRepository.findFirstBy();
        assertThat(highestScoreEntityOptional.isEmpty());
    }
}