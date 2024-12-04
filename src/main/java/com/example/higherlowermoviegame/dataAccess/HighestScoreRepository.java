package com.example.higherlowermoviegame.dataAccess;

import com.example.higherlowermoviegame.entities.HighestScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HighestScoreRepository extends JpaRepository<HighestScoreEntity, Long> {
    Optional<HighestScoreEntity> findFirstBy();
}
