package com.luka.movieRestApp.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String genre;
	@ManyToMany(mappedBy = "genres")
	private Set<Movie> movies;
	
	public Genre() {}

	public Genre(String genre, Set<Movie> movies) {
		this.genre = genre;
		this.movies = movies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", genre=" + genre + ", movies=" + movies + "]";
	}
	
	
	
	
	
}
