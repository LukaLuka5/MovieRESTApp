package com.luka.movieRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luka.movieRestApp.entities.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {

}
