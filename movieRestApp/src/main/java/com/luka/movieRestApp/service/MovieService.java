package com.luka.movieRestApp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luka.movieRestApp.entities.Genre;
import com.luka.movieRestApp.entities.Movie;
import com.luka.movieRestApp.repositories.GenreRepo;
import com.luka.movieRestApp.repositories.MovieRepo;

@Service
public class MovieService {
	
	private MovieRepo movieRepo;
	private GenreRepo genreRepo;

	public MovieService(MovieRepo movieRepo, GenreRepo genreRepo) {
		this.movieRepo = movieRepo;
		this.genreRepo = genreRepo;
	}
	public Movie saveMovie(Movie movie) {
		Set<Genre> genres = new HashSet<Genre>();
		//get genres from database first(genres that user added to a movie) instead of saving them directly to avoid duplication of genre entries in database
		for (Genre genre : movie.getGenres()) {
			genres.add(genreRepo.findGenreByGenre(genre.getGenre()));
		}
		movie.setGenres(genres);
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
