package com.luka.movieRestApp.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luka.movieRestApp.entities.Genre;
import com.luka.movieRestApp.entities.Movie;
import com.luka.movieRestApp.repositories.MovieRepo;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

	@Mock
	MovieRepo movieRepo;
	@InjectMocks
	MovieService movieService;
	
	Movie movie;
	Set<Genre> genres = new HashSet<>();
	
	@BeforeEach
	public void setUp(){
		genres.add(new Genre());
		movie = new Movie("Test Title", genres, "description");
	}
	
	@Test
	void testSaveMovie() {
		when(movieRepo.save(any(Movie.class))).thenReturn(movie);
		
		Movie returnedMovie = movieService.saveMovie(new Movie());
		
		assertThat(returnedMovie).isEqualTo(movie);
	}

	@Test
	void testGetMovies() {
		when(movieRepo.findAll()).thenReturn(Lists.newArrayList(new Movie(),new Movie()));
		
		List<Movie> returnedMovies = movieService.getMovies();
		
		assertThat(2).isEqualTo(returnedMovies.size());
	}

	@Test
	void testGetMovie() {
		when(movieRepo.findById(anyLong())).thenReturn(Optional.of(movie));
		
		Optional<Movie> returnedMovie = movieService.getMovie(2l);
		
		assertThat(returnedMovie.get()).isEqualTo(movie);
	}

	@Test
	void testUpdateMovie() {
		Movie updatedMovie = new Movie();
		updatedMovie.setTitle("Updated Title Check");
		
		when(movieRepo.findById(anyLong())).thenReturn(Optional.of(movie));
		when(movieRepo.save(any(Movie.class))).thenReturn(movie);
		
		Movie returnUpdatedMovie = movieService.updateMovie(updatedMovie, anyLong());
		
		assertThat(updatedMovie.getTitle()).isEqualTo(returnUpdatedMovie.getTitle());
	}

	@Test
	void testDeleteMovie() {
		movieService.deleteMovie(anyLong());
		verify(movieRepo).deleteById(anyLong());
		}

}
