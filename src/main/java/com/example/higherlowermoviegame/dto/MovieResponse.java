package com.example.higherlowermoviegame.dto;

import com.example.higherlowermoviegame.enums.Category;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieResponse {
    private String originalTitle;
    private LocalDate releaseDate;
    private String tagline;

    private Double revenue;
    private Double runtime;
    private Double popularity;
    private Double voteAverage;

    public Double getValueByCategory(String category){
        if (category == Category.POPULARITY.name()){
            return this.getPopularity();
        } else if (category == Category.VOTE_AVERAGE.name()){
            return this.getVoteAverage();
        } else if (category == Category.RUNTIME.name()){
            return this.getRuntime();
        } else {
            return this.getRevenue();
        }
    }
}
