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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="Title can't be empty")
	private String title;

    @ManyToMany(fetch = FetchType.EAGER)//default
    @JoinTable(
            name = "Movie_Genre", 
            joinColumns = { @JoinColumn(name = "movie_id",referencedColumnName="id") }, 
            inverseJoinColumns = { @JoinColumn(name = "genre_id",referencedColumnName="id") }
        )
    @NotEmpty(message = "You have to add at least one genre of the movie")
	private Set<Genre> genres;
    @OneToMany(mappedBy = "movie", cascade=CascadeType.ALL)
	private List<@Size(min = 1, max = 10) Rating> ratings;
    @NotBlank
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
