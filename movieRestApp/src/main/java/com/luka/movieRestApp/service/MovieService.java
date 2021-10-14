package com.luka.movieRestApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luka.movieRestApp.entities.Movie;
import com.luka.movieRestApp.repositories.MovieRepo;

@Service
public class MovieService {
	
	private MovieRepo movieRepo;

	public MovieService(MovieRepo movieRepo) {
		this.movieRepo = movieRepo;
	}
	public Movie saveMovie(Movie movie) {
		return movieRepo.save(movie);
	}
	@Transactional(readOnly = true)
	public List<Movie> getMovies(){
		return movieRepo.findAll();
	}
	@Transactional(readOnly = true)
	public Optional<Movie> getMovie(Long id) {
		return movieRepo.findById(id);
	}
	public Movie updateMovie(Movie updatedMovie, Long id) {
		Optional<Movie> movieOpt = movieRepo.findById(id);
		Movie movie = movieOpt.get();
		movie.setTitle(updatedMovie.getTitle());
		movie.setGenres(updatedMovie.getGenres());
		movie.setDescription(updatedMovie.getDescription());
		
		movieRepo.save(movie);
		return movie;
	}
	public void deleteMovie(Long id) {
		movieRepo.deleteById(id);;
	}
	
}
