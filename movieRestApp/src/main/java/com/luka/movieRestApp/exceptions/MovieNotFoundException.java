package com.luka.movieRestApp.exceptions;

public class MovieNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotFoundException(Long id) {
		super("Can't find movie with id + " + id);
	}
}
