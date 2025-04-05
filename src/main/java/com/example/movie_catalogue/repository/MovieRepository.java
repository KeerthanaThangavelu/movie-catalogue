package com.example.movie_catalogue.repository;

import com.example.movie_catalogue.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByMovieId(Long movieId);

    void deleteByMovieId(Long movieId);
}