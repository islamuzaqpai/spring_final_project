package com.example.demo.test.dto;


import com.example.demo.restApi.dto.ActorDto;
import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.dto.MovieDto;
import com.example.demo.restApi.entity.Actor;
import com.example.demo.restApi.entity.Director;
import com.example.demo.restApi.entity.Movie;
import com.example.demo.restApi.mapper.MovieMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class MovieMapperTest {

    @Autowired
    private MovieMapper movieMapper;
    @Test
    void toDtoTest() {

        Movie movie = new Movie(1L, "First", null, null);

        Director director = new Director(1L, "First", null);

        Actor actor1 = new Actor(1L, "First", null);
        Actor actor2 = new Actor(2L, "Second", null);

        movie.setDirector(director);
        movie.setActors(List.of(actor1, actor2));

        MovieDto movieDto = movieMapper.toDto(movie);

        Assertions.assertNotNull(movieDto);
        Assertions.assertNotNull(movieDto.getId());
        Assertions.assertNotNull(movieDto.getTitleDto());
        Assertions.assertNotNull(movieDto.getDirectorId());

        Assertions.assertEquals(movie.getId(), movieDto.getId());
        Assertions.assertEquals(movie.getTitle(), movieDto.getTitleDto());
        Assertions.assertEquals(movie.getDirector().getId(), movieDto.getDirectorId());

        List<Long> actorsIds = movie.getActors().stream().map(Actor::getId).collect(Collectors.toList());

        Assertions.assertEquals(actorsIds, movieDto.getActorsIds());
    }

    @Test
    void toEntityTest() {
        MovieDto movieDto = new MovieDto(1L, "First", null, null);

        DirectorDto directorDto = new DirectorDto(1L, "First", null);

        ActorDto actorDto1 = new ActorDto(1L, "First", null);
        ActorDto actorDto2 = new ActorDto(2L, "Second", null);

        movieDto.setDirectorId(directorDto.getId());
        movieDto.setActorsIds(List.of(actorDto1.getId(), actorDto2.getId()));

        Movie movie = movieMapper.toEntity(movieDto);

        Assertions.assertNotNull(movie);
        Assertions.assertNotNull(movie.getId());
        Assertions.assertNotNull(movie.getTitle());
        Assertions.assertNotNull(movie.getDirector());

        Assertions.assertEquals(movie.getId(), movieDto.getId());
        Assertions.assertEquals(movie.getTitle(), movieDto.getTitleDto());
        Assertions.assertEquals(movie.getDirector().getId(), movieDto.getDirectorId());

        List<Long> actorsIds = movie.getActors().stream().map(Actor::getId).collect(Collectors.toList());

        Assertions.assertEquals(actorsIds, movieDto.getActorsIds());
    }

    @Test
    void toDtoListTest() {
        Director director1 = new Director(1L, "First", null);
        Director director2 = new Director(2L, "Second", null);
        Director director3 = new Director(3L, "Third", null);

        Actor actor1 = new Actor(1L, "First", null);
        Actor actor2 = new Actor(2L, "Second", null);
        Actor actor3 = new Actor(3L, "Third", null);

        Movie movie1 = new Movie(1L, "First", null, null);
        Movie movie2 = new Movie(2L, "Second", null, null);
        Movie movie3 = new Movie(3L, "Third", null, null);

        movie1.setDirector(director1);
        movie2.setDirector(director2);
        movie3.setDirector(director3);

        movie1.setActors(List.of(actor1));
        movie2.setActors(List.of(actor2));
        movie3.setActors(List.of(actor3));

        List<Movie> movies = List.of(movie1, movie2, movie3);

        List<MovieDto> moviesDto = movieMapper.toDtoList(movies);

        Assertions.assertNotNull(moviesDto);
        Assertions.assertNotEquals(moviesDto.size(), 0);

        for (int i = 0; i < moviesDto.size(); i++) {
            MovieDto movieDto = moviesDto.get(i);

            Movie movie = movies.get(i);

            Assertions.assertNotNull(movieDto);
            Assertions.assertNotNull(movieDto.getId());
            Assertions.assertNotNull(movieDto.getTitleDto());
            Assertions.assertNotNull(movieDto.getDirectorId());
            Assertions.assertNotNull(movieDto.getActorsIds());

            Assertions.assertEquals(movie.getId(), movieDto.getId());
            Assertions.assertEquals(movie.getTitle(), movieDto.getTitleDto());
            Assertions.assertEquals(movie.getDirector().getId(), movieDto.getDirectorId());

            List<Long> actorsIds = movie.getActors().stream().map(Actor::getId).collect(Collectors.toList());

            Assertions.assertEquals(actorsIds, movieDto.getActorsIds());
        }
    }
}
