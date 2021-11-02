package com.luka.movieRestApp.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luka.movieRestApp.entities.Movie;
import com.luka.movieRestApp.exceptions.MovieNotFoundException;
import com.luka.movieRestApp.modelAssembler.MovieModelAssembler;
import com.luka.movieRestApp.service.MovieService;

@RestController
public class MovieController {

	MovieService movieService;
	MovieModelAssembler assembler;

	public MovieController(MovieService movieService, MovieModelAssembler assembler) {
		this.movieService = movieService;
		this.assembler = assembler;
	}
	
	@GetMapping("/movies")
	public CollectionModel<EntityModel<Movie>> getMovies(){
		List<EntityModel<Movie>> movies = movieService.getMovies().stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return CollectionModel.of(movies, linkTo(methodOn(MovieController.class).getMovies()).withSelfRel());
	}
	@GetMapping("/movies/{id}")
	public EntityModel<Movie> getMovie(@PathVariable Long id) {
		Movie movie = movieService.getMovie(id).orElseThrow(() -> new MovieNotFoundException(id));
		
		return assembler.toModel(movie);
	}
	@PostMapping("/movies")
	public ResponseEntity<EntityModel<Movie>> saveMovie(@Valid @RequestBody Movie newMovie) {
		EntityModel<Movie> entityModel = assembler.toModel(movieService.saveMovie(newMovie));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
				.toUri()).body(entityModel);
	}
	@PutMapping("/movies/{id}")
	public ResponseEntity<EntityModel<Movie>> updateMovie(@PathVariable Long movieId, @RequestBody Movie updatedMovieInfo) {
		Movie movie = movieService.getMovie(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
			
		movie.setTitle(updatedMovieInfo.getTitle());
		movie.setDescription(updatedMovieInfo.getDescription());
		movie.setGenres(updatedMovieInfo.getGenres());
					

		EntityModel<Movie> entityModel = assembler.toModel(movie);
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF)
				.toUri()).body(entityModel);
	}
	@DeleteMapping("/movies/{id}")
	public void deleteMovie(@PathVariable Long id) {
		movieService.deleteMovie(id);
	}
	
}
