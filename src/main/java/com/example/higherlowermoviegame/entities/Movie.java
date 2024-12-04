package com.example.higherlowermoviegame.entities;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Table(name = "movie")
@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "original_title")
    private String originalTitle;

    @NotNull
    @Column(name = "overview", length = 1000)
    private String overview;

    @NotNull
    @Column(name = "popularity")
    private Double popularity;

    @NotNull
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @NotNull
    @Column(name = "revenue")
    private Double revenue;

    @NotNull
    @Column(name = "runtime")
    private Double runtime;

    @NotNull
    @Column(name = "tagline")
    private String tagline;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "vote_average")
    private Double voteAverage;

    @NotNull
    @Column(name = "vote_count")
    private Long voteCount;

}
