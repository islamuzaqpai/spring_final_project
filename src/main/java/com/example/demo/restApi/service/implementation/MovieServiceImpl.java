package com.example.demo.restApi.service.implementation;

import com.example.demo.restApi.dto.MovieDto;
import com.example.demo.restApi.entity.Movie;
import com.example.demo.restApi.mapper.MovieMapper;
import com.example.demo.restApi.repository.MovieRepository;
import com.example.demo.restApi.service.MovieServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieServiceInterface {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    public MovieDto getMovieById(Long id) {
        return movieMapper.toDto(movieRepository.findById(id).orElse(null));
    }

    public List<MovieDto> getAllMovies() {
        return movieMapper.toDtoList(movieRepository.findAll());
    }

    public MovieDto addMovie(MovieDto movieDto) {
        return movieMapper.toDto(movieRepository.save(movieMapper.toEntity(movieDto)));
    }

    public MovieDto updateMovie(Long id, MovieDto newMovieDto) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie == null) {
            return null;
        }

        movie.setTitle(newMovieDto.getTitleDto());

        movie.getActors().clear();
        movie.setActors(movieMapper.idsToActors(newMovieDto.getActorsIds()));

        movie.setDirector(movieMapper.idToDirector(newMovieDto.getDirectorId()));

        return movieMapper.toDto(movieRepository.save(movie));
    }

    public boolean deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);

        if (movie != null) {

            movie.getActors().clear();
            movie.setDirector(null);

            movieRepository.delete(movie);

            return true;
        }

        return false;
    }
}
