package com.luka.movieRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luka.movieRestApp.entities.Genre;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {
	public Genre findGenreByGenre(String genre);
}
