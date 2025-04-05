package com.example.movie_catalogue.controller;

import com.example.movie_catalogue.model.Movie;
import com.example.movie_catalogue.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("movies", movieService.getTrendingMovies());
        return "index";
    }

    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("isFavorite", movieService.isFavorite(id));
        return "movie-details";
    }

    @PostMapping("/movie/{movieId}/favorite")
    public String toggleFavorite(@PathVariable Long movieId, @RequestParam boolean add) {
        Movie movie = movieService.getMovieDetails(movieId);
        if (add) {
            movieService.addToFavorites(movie);
        } else {
            movieService.removeFromFavorites(movieId);
        }
        return "redirect:/movie/" + movieId;
    }

    @GetMapping("/favorites")
    public String favorites(Model model) {
        model.addAttribute("favorites", movieService.getFavoriteMovies());
        return "favorites";
    }

    @PostMapping("/favorite")
    public String addFavorite(@ModelAttribute Movie movie) {
        movieService.addToFavorites(movie);
        return "redirect:/favorites";
    }

    @PostMapping("/remove-favorite")
    public String removeFavorite(@RequestParam Long id, Model model) {
        movieService.removeFavorite(id);
        model.addAttribute("message", "Movie removed from favorites.");
        return "redirect:/favorites";
    }

    @PostMapping("/remove-favorite/movie")
    public String removeFavorite(@ModelAttribute Movie movie) {
        movieService.removeFavoriteByMovieId(movie.getMovieId());
        return  "redirect:/movie/" + movie.getMovieId();
    }
}
