package com.example.demo.restApi.controller;

import com.example.demo.restApi.dto.MovieDto;
import com.example.demo.restApi.service.implementation.MovieServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class MovieController {
    private final MovieServiceImpl movieService;

    @GetMapping("/movies/{id}")
    public MovieDto getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/movies")
    public List<MovieDto> getAllDirectors() {
        return movieService.getAllMovies();
    }

    @PostMapping("/movies")
    public MovieDto addDirector(@RequestBody MovieDto movieDto) {
        return movieService.addMovie(movieDto);
    }

    @PutMapping("/movies/{id}")
    public MovieDto updateMovie(@PathVariable Long id, @RequestBody MovieDto newMovieDto) {
        return movieService.updateMovie(id, newMovieDto);
    }

    @DeleteMapping("/movies/{id}")
    public boolean deleteMovie(@PathVariable Long id) {
        return movieService.deleteMovie(id);
    }
}
