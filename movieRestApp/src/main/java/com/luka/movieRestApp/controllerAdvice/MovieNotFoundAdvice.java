package com.luka.movieRestApp.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.luka.movieRestApp.exceptions.MovieNotFoundException;

@ControllerAdvice
public class MovieNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String movieNotFoundHandler(MovieNotFoundException ex) {
		return ex.getMessage();
	}
}
