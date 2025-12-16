package com.example.demo.restApi.mapper;

import com.example.demo.restApi.dto.MovieDto;
import com.example.demo.restApi.entity.Actor;
import com.example.demo.restApi.entity.Director;
import com.example.demo.restApi.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "titleDto", source = "title")
    @Mapping(target = "directorId", source = "director", qualifiedByName = "directorToId")
    @Mapping(target = "actorsIds", source = "actors", qualifiedByName = "actorsToIds")
    MovieDto toDto(Movie movie);
    List<MovieDto> toDtoList(List<Movie> movies);

    @Mapping(target = "title", source = "titleDto")
    @Mapping(target = "director", source = "directorId", qualifiedByName = "idToDirector")
    @Mapping(target = "actors", source = "actorsIds", qualifiedByName = "idsToActors")
    Movie toEntity(MovieDto movieDto);

    @Named("directorToId")
    default Long directorToId(Director director) {
        if (director == null) {
            return null;
        }

        return director.getId();
    }

    @Named("actorsToIds")
    default List<Long> actorsToIds(List<Actor> actors) {
        if (actors == null || actors.isEmpty()) {
            return null;
        }

        List<Long> actorsIds = new ArrayList<>();

        for (Actor actor : actors) {
            actorsIds.add(actor.getId());
        }

        return actorsIds;
    }

    @Named("idToDirector")
    default Director idToDirector(Long directorId) {
        if (directorId == null) {
            return null;
        }

        Director director = new Director();
        director.setId(directorId);
        return director;
    }

    @Named("idsToActors")
    default List<Actor> idsToActors(List<Long> actorsIds) {
        if (actorsIds == null || actorsIds.isEmpty()) {
            return null;
        }

        List<Actor> actors = new ArrayList<>();

        for (Long id : actorsIds) {
            Actor actor = new Actor();
            actor.setId(id);
            actors.add(actor);
        }

        return actors;
    }
}
