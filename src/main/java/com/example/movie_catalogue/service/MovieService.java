package com.example.movie_catalogue.service;

import com.example.movie_catalogue.model.Movie;
import com.example.movie_catalogue.repository.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Value("${tmdb.api-key}")
    private String apiKey;

    @Value("${tmdb.base-url}")
    private String baseUrl;

    //private static final String TMDB_API_KEY = "e0e5c1fa525994b48645a601be5960d8"; // Replace with your TMDb API Key
    //private static final String TMDB_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + TMDB_API_KEY;

    @Autowired
    private MovieRepository movieRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Movie> getTrendingMovies() {
        JsonNode forObject = restTemplate.getForObject(baseUrl + "/popular?api_key=" + apiKey, JsonNode.class);
        List<Movie> movies = new ArrayList<>();
        ArrayNode results = (ArrayNode) forObject.get("results");

        for (JsonNode node : results) {
            Movie movie = new Movie();
            movie.setId(node.get("id").asLong());
            movie.setMovieId(node.get("id").asLong());
            movie.setTitle(node.get("title").asText());
            movie.setDescription(node.get("overview").asText());
            movie.setPosterPath(node.get("poster_path").asText());
            movie.setReleaseDate(node.get("release_date").asText());
            movie.setRating(node.get("vote_average").asDouble());
            movies.add(movie);
        }
        return movies;
    }

    public Movie getMovieById(Long id) {
        String url =  baseUrl + "/" + id + "?api_key=" + apiKey;
        JsonNode node = restTemplate.getForObject(url, JsonNode.class);
        Movie movie = new Movie();
        movie.setMovieId(id);
        movie.setTitle(node.get("title").asText());
        movie.setDescription(node.get("overview").asText());
        movie.setPosterPath(node.get("poster_path").asText());
        movie.setReleaseDate(node.get("release_date").asText());
        movie.setRating(node.get("vote_average").asDouble());
        return movie;
    }

    @Transactional
    public void addToFavorites(Movie movie) {
        if (!movieRepository.existsByMovieId(movie.getMovieId())) {
            movieRepository.save(movie);
        }
    }

    public List<Movie> getFavoriteMovies() {
        return movieRepository.findAll();
    }

    public void removeFavorite(Long id) {
        movieRepository.deleteById(id);
    }

    @Transactional
    public void removeFavoriteByMovieId(Long id) {
        movieRepository.deleteByMovieId(id);
    }

    public boolean isFavorite(Long id) {
        return movieRepository.existsByMovieId(id);
    }

    public Movie getMovieDetails(Long movieId) {
        return getMovieById(movieId);
    }

    public void removeFromFavorites(Long id) {
        movieRepository.deleteById(id);
    }
}
