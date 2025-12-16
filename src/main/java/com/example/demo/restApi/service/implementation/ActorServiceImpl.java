package com.example.demo.restApi.service.implementation;

import com.example.demo.restApi.dto.ActorDto;
import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.entity.Actor;
import com.example.demo.restApi.entity.Director;
import com.example.demo.restApi.entity.Movie;
import com.example.demo.restApi.mapper.ActorMapper;
import com.example.demo.restApi.repository.ActorRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ActorServiceImpl {
    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public ActorDto getActorById(Long id) {
        return actorMapper.toDto(actorRepository.findById(id).orElse(null));
    }

    public List<ActorDto> getAllDirectors() {
        return actorMapper.toDtoList(actorRepository.findAll());
    }

    public ActorDto addActor(ActorDto actorDto) {
        return actorMapper.toDto(actorRepository.save(actorMapper.toEntity(actorDto)));
    }

    public ActorDto updateActor(Long id, ActorDto newActorDto) {
        Actor actor = actorRepository.findById(id).orElse(null);

        if (actor == null) {
            return null;
        }

        actor.setName(newActorDto.getNameDto());

        return actorMapper.toDto(actorRepository.save(actor));
    }

    public boolean deleteActor(Long id) {
        Actor actor = actorRepository.findById(id).orElse(null);

        if (actor != null) {
            for (Movie movie : actor.getMovies()) {
                movie.setDirector(null);
            }
            actorRepository.delete(actor);
            return true;
        }

        return false;
    }
}
