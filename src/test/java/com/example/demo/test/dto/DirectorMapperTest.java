package com.example.demo.test.dto;

import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.entity.Director;
import com.example.demo.restApi.entity.Movie;
import com.example.demo.restApi.mapper.DirectorMapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
}
