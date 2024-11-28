package com.example.higherlowermoviegame.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "HIGHEST_SCORE")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class HighestScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "NORMAL_MODE")
    Integer normalMode;

    @Column(name = "HARD_MODE")
    Integer hardMode;
}
