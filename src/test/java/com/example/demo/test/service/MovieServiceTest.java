package com.example.demo.test.service;

import com.example.demo.restApi.dto.ActorDto;
import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.dto.MovieDto;
import com.example.demo.restApi.entity.Actor;
import com.example.demo.restApi.entity.Director;
import com.example.demo.restApi.repository.ActorRepository;
import com.example.demo.restApi.repository.DirectorRepository;
import com.example.demo.restApi.service.implementation.MovieServiceImpl;
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

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectorRepository directorRepository;

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
        }
    }

    @Test
    void addMovieTest() {
        Director director = new Director();
        director.setName("First");
        director = directorRepository.save(director);

        Actor actor1 = new Actor();
        actor1.setName("First");
        actor1 = actorRepository.save(actor1);

        Actor actor2 = new Actor();
        actor2.setName("Second");
        actor2 = actorRepository.save(actor2);

        Actor actor3 = new Actor();
        actor3.setName("Third");
        actor3 = actorRepository.save(actor3);

        MovieDto movieDto = new MovieDto();
        movieDto.setTitleDto("First");
        movieDto.setDirectorId(director.getId());
        movieDto.setActorsIds(List.of(actor1.getId(), actor2.getId(), actor3.getId()));

        MovieDto savedMovie = movieService.addMovie(movieDto);

        Assertions.assertNotNull(savedMovie.getId());
        Assertions.assertEquals(movieDto.getTitleDto(), savedMovie.getTitleDto());
        Assertions.assertEquals(director.getId(), savedMovie.getDirectorId());
        Assertions.assertEquals(3, savedMovie.getActorsIds().size());

        MovieDto fetchedMovie = movieService.getMovieById(savedMovie.getId());
        Assertions.assertEquals(savedMovie.getId(), fetchedMovie.getId());
        Assertions.assertEquals(savedMovie.getTitleDto(), fetchedMovie.getTitleDto());
        Assertions.assertEquals(savedMovie.getDirectorId(), fetchedMovie.getDirectorId());
        Assertions.assertEquals(savedMovie.getActorsIds(), fetchedMovie.getActorsIds());
    }



    @Test
    void updateMovieTest() {
        List<MovieDto> movies = movieService.getAllMovies();

        Random random = new Random();
        int randomIndex = random.nextInt(movies.size());
        Long movieId = movies.get(randomIndex).getId();

        Director director = new Director();
        director.setName("updated");
        director = directorRepository.save(director);

        Actor actor = new Actor();
        actor.setName("updated");
        actor = actorRepository.save(actor);

        MovieDto movieDto = new MovieDto();
        movieDto.setId(movieId);
        movieDto.setTitleDto("updated");
        movieDto.setDirectorId(director.getId());
        movieDto.setActorsIds(List.of(actor.getId()));

        MovieDto updatedMovie = movieService.updateMovie(movieId, movieDto);

        Assertions.assertNotNull(updatedMovie.getId());
        Assertions.assertEquals(movieId, updatedMovie.getId());
        Assertions.assertEquals("updated", updatedMovie.getTitleDto());
        Assertions.assertEquals(director.getId(), updatedMovie.getDirectorId());
        Assertions.assertEquals(1, updatedMovie.getActorsIds().size());
        Assertions.assertEquals(actor.getId(), updatedMovie.getActorsIds().get(0));

        MovieDto fetchedMovie = movieService.getMovieById(movieId);
        Assertions.assertEquals(updatedMovie.getId(), fetchedMovie.getId());
        Assertions.assertEquals(updatedMovie.getTitleDto(), fetchedMovie.getTitleDto());
        Assertions.assertEquals(updatedMovie.getDirectorId(), fetchedMovie.getDirectorId());
        Assertions.assertEquals(updatedMovie.getActorsIds(), fetchedMovie.getActorsIds());
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
