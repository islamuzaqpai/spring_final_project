package com.example.demo.restApi.mapper;

import com.example.demo.restApi.dto.ActorDto;
import com.example.demo.restApi.entity.Actor;
import com.example.demo.restApi.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "moviesIds", source = "movies", qualifiedByName = "moviesToIds")
    ActorDto toDto(Actor actor);
    List<ActorDto> toDtoList(List<Actor> actors);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "movies", source = "moviesIds", qualifiedByName = "idsToMovies")
    Actor toEntity(ActorDto actorDto);

    @Named("moviesToIds")
    default List<Long> moviesToIds(List<Movie> movies) {
        if (movies == null || movies.isEmpty()) {
            return null;
        }

        List<Long> moviesIds = new ArrayList<>();

        for (Movie movie : movies) {
            moviesIds.add(movie.getId());
        }

        return moviesIds;
    }

    @Named("idsToMovies")
    default List<Movie> idsToMovies(List<Long> moviesIds) {
        if (moviesIds == null || moviesIds.isEmpty()) {
            return null;
        }

        List<Movie> movies = new ArrayList<>();

        for (Long id : moviesIds) {
            Movie movie = new Movie();
            movie.setId(id);
            movies.add(movie);
        }

        return movies;
    }
}
