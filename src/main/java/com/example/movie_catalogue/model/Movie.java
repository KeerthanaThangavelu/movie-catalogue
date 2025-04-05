package com.example.movie_catalogue.model;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String posterPath;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private String releaseDate;
    private Double rating;
    private Long movieId;

    // Constructors
    public Movie() {}

    public Movie(String title, String posterPath, String description, String releaseDate, Double rating, Long movieId) {
        this.title = title;
        this.posterPath = posterPath;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.movieId = movieId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
