package com.example.demo.test.dto;

import com.example.demo.restApi.dto.ActorDto;
import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.dto.MovieDto;
import com.example.demo.restApi.entity.Actor;
import com.example.demo.restApi.entity.Director;
import com.example.demo.restApi.entity.Movie;
import com.example.demo.restApi.mapper.ActorMapper;
import com.example.demo.restApi.mapper.DirectorMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class ActorMapperTest {

    @Autowired
    private ActorMapper actorMapper;

    @Test
    void toDtoTest() {

        Actor actor = new Actor(1L, "First", null);

        Movie movie1 = new Movie(1L, "First", null, null);
        Movie movie2 = new Movie(2L, "Second", null, null);

        actor.setMovies(List.of(movie1, movie2));

        ActorDto actorDto = actorMapper.toDto(actor);

        Assertions.assertNotNull(actorDto);
        Assertions.assertNotNull(actorDto.getId());
        Assertions.assertNotNull(actorDto.getNameDto());
        Assertions.assertNotNull(actorDto.getMoviesIds());

        Assertions.assertEquals(actor.getId(), actorDto.getId());
        Assertions.assertEquals(actor.getName(), actorDto.getNameDto());

        List<Long> moviesIds = actor.getMovies().stream().map(Movie::getId).collect(Collectors.toList());

        Assertions.assertEquals(moviesIds, actorDto.getMoviesIds());
    }

    @Test
    void toEntityTest() {
        ActorDto actorDto = new ActorDto(1L, "First", null);

        MovieDto movieDto1 = new MovieDto(1L, "First", null, null);
        MovieDto movieDto2 = new MovieDto(2L, "Second", null, null);

        actorDto.setMoviesIds(List.of(movieDto1.getId(), movieDto2.getId()));

        Actor actor = actorMapper.toEntity(actorDto);

        Assertions.assertNotNull(actor);
        Assertions.assertNotNull(actor.getId());
        Assertions.assertNotNull(actor.getName());
        Assertions.assertNotNull(actor.getMovies());

        Assertions.assertEquals(actor.getId(), actorDto.getId());
        Assertions.assertEquals(actor.getName(), actorDto.getNameDto());

        List<Long> moviesIds = actor.getMovies().stream().map(Movie::getId).collect(Collectors.toList());

        Assertions.assertEquals(moviesIds, actorDto.getMoviesIds());
    }

    @Test
    void toDtoListTest() {
        Actor actor1 = new Actor(1L, "First", null);
        Actor actor2 = new Actor(2L, "Second", null);
        Actor actor3 = new Actor(3L, "Third", null);

        Movie movie1 = new Movie(1L, "First", null, null);
        Movie movie2 = new Movie(2L, "Second", null, null);
        Movie movie3 = new Movie(3L, "Third", null, null);

        actor1.setMovies(List.of(movie1));
        actor2.setMovies(List.of(movie2));
        actor3.setMovies(List.of(movie3));

        List<Actor> actors = List.of(actor1, actor2, actor3);

        List<ActorDto> actorsDto = actorMapper.toDtoList(actors);

        Assertions.assertNotNull(actorsDto);
        Assertions.assertNotEquals(actorsDto.size(), 0);

        for (int i = 0; i < actorsDto.size(); i++) {
            ActorDto actorDto= actorsDto.get(i);

            Actor actor = actors.get(i);

            Assertions.assertNotNull(actorDto);
            Assertions.assertNotNull(actorDto.getId());
            Assertions.assertNotNull(actorDto.getNameDto());
            Assertions.assertNotNull(actorDto.getMoviesIds());

            Assertions.assertEquals(actor.getId(), actorDto.getId());
            Assertions.assertEquals(actor.getName(), actorDto.getNameDto());

            List<Long> moviesIds = actor.getMovies().stream().map(Movie::getId).collect(Collectors.toList());

            Assertions.assertEquals(moviesIds, actorDto.getMoviesIds());
        }
    }
}
