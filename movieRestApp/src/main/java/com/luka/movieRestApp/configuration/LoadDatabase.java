package com.luka.movieRestApp.configuration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.luka.movieRestApp.entities.Movie;
import com.luka.movieRestApp.repositories.GenreRepo;
import com.luka.movieRestApp.repositories.MovieRepo;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase(MovieRepo movieRepository, GenreRepo genreRepository) {
		return args -> {
			log.info("Preloading "
					+ movieRepository.save(new Movie("Lord of the Rings", Stream.of(genreRepository.findGenreByGenre("Adventure"),genreRepository.findGenreByGenre("Fantasy")).collect(Collectors.toSet()),"Some description...")));
			log.info("Preloading " 
					+ movieRepository.save(new Movie("Star Wars", Stream.of(genreRepository.findGenreByGenre("Action")).collect(Collectors.toSet()),"Some description...")));
			log.info("Preloading " 
					+ movieRepository.save(new Movie("Star", Stream.of(genreRepository.findGenreByGenre("Comedy")).collect(Collectors.toSet()),"Some description...")));
			};
	}
}
