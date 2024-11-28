package com.example.higherlowermoviegame.dataAccess;

import com.example.higherlowermoviegame.entities.HighestScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HighestScoreRepository extends JpaRepository<HighestScoreEntity, Long> {
    Optional<HighestScoreEntity> findFirstBy();
}
