package com.example.demo.test.service;

import com.example.demo.restApi.dto.ActorDto;
import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.dto.MovieDto;
import com.example.demo.restApi.service.implementation.MovieServiceImpl;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class MovieServiceTest {

    @Autowired
    private MovieServiceImpl movieService;

    @Test
    void getMovieByIdTest() {
        Random random = new Random();

        int randomIndex = random.nextInt(movieService.getAllMovies().size());

        Long someIndex = movieService.getAllMovies().get(randomIndex).getId();

        MovieDto movieDto = movieService.getMovieById(someIndex);
        Assertions.assertNotNull(movieDto);
        Assertions.assertNotNull(movieDto.getId());
        Assertions.assertNotNull(movieDto.getTitleDto());
        Assertions.assertNotNull(movieDto.getDirectorId());
        Assertions.assertNotNull(movieDto.getActorsIds());

        MovieDto check = movieService.getMovieById(-1L);

        Assertions.assertNull(check);
    }

    @Test
    void getAllMoviesTest() {

        List<MovieDto> moviesDto  = movieService.getAllMovies();

        Assertions.assertNotEquals(0, moviesDto.size());
        Assertions.assertNotNull(moviesDto);

        for (MovieDto movieDto : moviesDto) {
            Assertions.assertNotNull(movieDto);
            Assertions.assertNotNull(movieDto.getId());
            Assertions.assertNotNull(movieDto.getTitleDto());
            Assertions.assertNotNull(movieDto.getTitleDto());
            Assertions.assertNotNull(movieDto.getDirectorId());
            Assertions.assertNotNull(movieDto.getActorsIds());
        }
    }

    @Test
    void addMovieTest() {

        MovieDto movieDto = new MovieDto(1L, "First", null, null);

        DirectorDto directorDto = new DirectorDto(1L, "First", null);

        ActorDto actorDto1 = new ActorDto(1L, "First", null);
        ActorDto actorDto2 = new ActorDto(2L, "Second", null);
        ActorDto actorDto3 = new ActorDto(3L, "Third", null);

        movieDto.setDirectorId(directorDto.getId());
        movieDto.setActorsIds(List.of(actorDto1.getId(), actorDto2.getId(), actorDto3.getId()));

        MovieDto add = movieService.addMovie(movieDto);

        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getTitleDto());
        Assertions.assertNotNull(add.getDirectorId());
        Assertions.assertNotNull(add.getActorsIds());


        Assertions.assertEquals(add.getId(), movieDto.getId());
        Assertions.assertEquals(add.getTitleDto(), movieDto.getTitleDto());
        Assertions.assertEquals(add.getDirectorId(), movieDto.getDirectorId());
        Assertions.assertEquals(add.getActorsIds(), movieDto.getActorsIds());

        MovieDto added = movieService.getMovieById(add.getId());

        Assertions.assertEquals(add.getId(), added.getId());
        Assertions.assertEquals(add.getTitleDto(), added.getTitleDto());
        Assertions.assertEquals(add.getDirectorId(), added.getDirectorId());
        Assertions.assertEquals(add.getActorsIds(), added.getActorsIds());
    }

    @Test
    void updateMovieTest() {

        Random random = new Random();

        int randomIndex = random.nextInt(movieService.getAllMovies().size());

        Long someIndex = movieService.getAllMovies().get(randomIndex).getId();

        MovieDto movieDto = new MovieDto(1L, "Update", null, null);

        DirectorDto directorDto = new DirectorDto(1L, "Update", null);

        ActorDto actorDto1 = new ActorDto(1L, "Update", null);
        ActorDto actorDto2 = new ActorDto(2L, "Update", null);
        ActorDto actorDto3 = new ActorDto(3L, "Update", null);

        movieDto.setDirectorId(directorDto.getId());
        movieDto.setActorsIds(List.of(actorDto1.getId(), actorDto2.getId(), actorDto3.getId()));

        MovieDto update = movieService.updateMovie(someIndex, movieDto);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getTitleDto());
        Assertions.assertNotNull(update.getDirectorId());
        Assertions.assertNotNull(update.getActorsIds());

        Assertions.assertEquals(update.getId(), movieDto.getId());
        Assertions.assertEquals(update.getTitleDto(), movieDto.getTitleDto());
        Assertions.assertEquals(update.getDirectorId(), movieDto.getDirectorId());
        Assertions.assertEquals(update.getActorsIds(), movieDto.getActorsIds());

        MovieDto updated = movieService.getMovieById(someIndex);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getTitleDto());
        Assertions.assertNotNull(updated.getDirectorId());
        Assertions.assertNotNull(updated.getActorsIds());

        Assertions.assertEquals(update.getId(), updated.getId());
        Assertions.assertEquals(update.getTitleDto(), updated.getTitleDto());
        Assertions.assertEquals(update.getDirectorId(), updated.getDirectorId());
        Assertions.assertEquals(update.getActorsIds(), updated.getActorsIds());
    }

    @Test
    void deleteMovieTest() {

        Random random = new Random();

        int randomIndex = random.nextInt(movieService.getAllMovies().size());

        Long someIndex = movieService.getAllMovies().get(randomIndex).getId();

        Assertions.assertTrue(movieService.deleteMovie(someIndex));

        MovieDto check = movieService.getMovieById(someIndex);
        Assertions.assertNull(check);
    }
}
