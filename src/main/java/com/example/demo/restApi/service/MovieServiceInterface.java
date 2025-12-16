package com.example.demo.restApi.service;

import com.example.demo.restApi.dto.MovieDto;

import java.util.List;

public interface MovieServiceInterface {
    MovieDto getMovieById(Long id);

    List<MovieDto> getAllMovies();

    MovieDto addMovie(MovieDto movieDto);

    MovieDto updateMovie(Long id, MovieDto newMovieDto);

    boolean deleteMovie(Long id);
}
