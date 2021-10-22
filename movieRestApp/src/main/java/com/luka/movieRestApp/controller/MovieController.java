package com.luka.movieRestApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luka.movieRestApp.entities.Movie;
import com.luka.movieRestApp.exceptions.MovieNotFoundException;
import com.luka.movieRestApp.service.MovieService;

@RestController
public class MovieController {

	MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping("/movies")
	public List<Movie> getMovies(){
		return movieService.getMovies();
	}
	@GetMapping("/movies/{id}")
	public Movie getMovie(@PathVariable Long id) {
		Optional<Movie> movieOpt = movieService.getMovie(id);
		return movieOpt.orElseThrow(() -> new MovieNotFoundException(id));
	}
	@PostMapping("/movies")
	public Movie saveMovie(@RequestBody Movie movie) {
		return movieService.saveMovie(movie);
	}
	@PutMapping("/movies/{id}")
	public Movie updateMovie(@PathVariable Long movieId, @RequestBody Movie updatedMovieInfo) {
		return movieService.updateMovie(updatedMovieInfo, movieId);	
	}
	@DeleteMapping("/movies/{id}")
	public void deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(id);
	}
	
}
