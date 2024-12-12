package com.example.higherlowermoviegame.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String originalTitle;

    @NotNull
    @Column(length = 1000)
    private String overview;

    @NotNull
    private Double popularity;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    private Double revenue;

    @NotNull
    private Double runtime;

    @NotNull
    private String tagline;

    @NotNull
    private String title;

    @NotNull
    private Double voteAverage;

    @NotNull
    private Long voteCount;

}
