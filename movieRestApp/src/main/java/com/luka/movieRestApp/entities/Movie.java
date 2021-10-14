package com.luka.movieRestApp.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Movie_Genre", 
            joinColumns = { @JoinColumn(name = "movie_id",referencedColumnName="id") }, 
            inverseJoinColumns = { @JoinColumn(name = "genre_id",referencedColumnName="id") }
        )
	private Set<Genre> genres;
    @OneToMany(mappedBy = "movie", cascade=CascadeType.ALL)
	private List<Rating> ratings;
	private String description;
	@OneToMany(mappedBy = "movie", cascade=CascadeType.ALL)
	private Set<Review> reviews;
	
	public Movie() {}

	public Movie(String title, Set<Genre> genres, String description) {
		this.title = title;
		this.genres = genres;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genre) {
		this.genres = genre;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genres=" + genres + ", ratings=" + ratings + ", description="
				+ description + ", reviews=" + reviews + "]";
	}


	
	
	
	
	
}
