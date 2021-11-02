package com.luka.movieRestApp.modelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.luka.movieRestApp.controller.MovieController;
import com.luka.movieRestApp.entities.Movie;

@Component
public class MovieModelAssembler implements RepresentationModelAssembler<Movie, EntityModel<Movie>> {

	@Override
	public EntityModel<Movie> toModel(Movie movie) {
		
		return EntityModel.of(movie,
			linkTo(methodOn(MovieController.class).getMovie(movie.getId())).withSelfRel(),
			linkTo(methodOn(MovieController.class).getMovies()).withRel("movies"));
	}

}
