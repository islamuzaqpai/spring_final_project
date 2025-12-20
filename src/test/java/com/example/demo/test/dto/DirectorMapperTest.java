package com.example.demo.test.dto;

import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.dto.MovieDto;
import com.example.demo.restApi.entity.Director;
import com.example.demo.restApi.entity.Movie;
import com.example.demo.restApi.mapper.DirectorMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class DirectorMapperTest {

    @Autowired
    private DirectorMapper directorMapper;

    @Test
    void toDtoTest() {

        Director director = new Director(1L, "First", null);

        Movie movie1 = new Movie(1L, "First", null, null);
        Movie movie2 = new Movie(2L, "Second", null, null);

        director.setMovies(List.of(movie1, movie2));

        DirectorDto directorDto = directorMapper.toDto(director);

        Assertions.assertNotNull(directorDto);
        Assertions.assertNotNull(directorDto.getId());
        Assertions.assertNotNull(directorDto.getNameDto());
        Assertions.assertNotNull(directorDto.getMoviesIds());

        Assertions.assertEquals(director.getId(), directorDto.getId());
        Assertions.assertEquals(director.getName(), directorDto.getNameDto());

        List<Long> moviesIds = director.getMovies().stream().map(Movie::getId).collect(Collectors.toList());

        Assertions.assertEquals(moviesIds, directorDto.getMoviesIds());
    }

    @Test
    void toEntityTest() {
        DirectorDto directorDto = new DirectorDto(1L, "First", null);

        MovieDto movieDto1 = new MovieDto(1L, "First", null, null);
        MovieDto movieDto2 = new MovieDto(2L, "Second", null, null);

        directorDto.setMoviesIds(List.of(movieDto1.getId(), movieDto2.getId()));

        Director director = directorMapper.toEntity(directorDto);

        Assertions.assertNotNull(director);
        Assertions.assertNotNull(director.getId());
        Assertions.assertNotNull(director.getName());
        Assertions.assertNotNull(director.getMovies());

        Assertions.assertEquals(director.getId(), directorDto.getId());
        Assertions.assertEquals(director.getName(), directorDto.getNameDto());

        List<Long> moviesIds = director.getMovies().stream().map(Movie::getId).collect(Collectors.toList());

        Assertions.assertEquals(moviesIds, directorDto.getMoviesIds());
    }

    @Test
    void toDtoListTest() {
        Director director1 = new Director(1L, "First", null);
        Director director2 = new Director(2L, "Second", null);
        Director director3 = new Director(3L, "Third", null);

        Movie movie1 = new Movie(1L, "First", null, null);
        Movie movie2 = new Movie(2L, "Second", null, null);
        Movie movie3 = new Movie(3L, "Third", null, null);

        director1.setMovies(List.of(movie1));
        director2.setMovies(List.of(movie2));
        director3.setMovies(List.of(movie3));

        List<Director> directors = List.of(director1, director2, director3);

        List<DirectorDto> directorsDto = directorMapper.toDtoList(directors);

        Assertions.assertNotNull(directorsDto);
        Assertions.assertNotEquals(directorsDto.size(), 0);

        for (int i = 0; i < directorsDto.size(); i++) {
            DirectorDto directorDto = directorsDto.get(i);

            Director director = directors.get(i);

            Assertions.assertNotNull(directorDto);
            Assertions.assertNotNull(directorDto.getId());
            Assertions.assertNotNull(directorDto.getNameDto());
            Assertions.assertNotNull(directorDto.getMoviesIds());

            Assertions.assertEquals(director.getId(), directorDto.getId());
            Assertions.assertEquals(director.getName(), directorDto.getNameDto());

            List<Long> moviesIds = director.getMovies().stream().map(Movie::getId).collect(Collectors.toList());

            Assertions.assertEquals(moviesIds, directorDto.getMoviesIds());
        }
    }
}
