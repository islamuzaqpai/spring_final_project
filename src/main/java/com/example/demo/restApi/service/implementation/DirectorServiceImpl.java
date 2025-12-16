package com.example.demo.restApi.service.implementation;

import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.entity.Director;
import com.example.demo.restApi.entity.Movie;
import com.example.demo.restApi.mapper.DirectorMapper;
import com.example.demo.restApi.repository.DirectorRepository;
import com.example.demo.restApi.service.DirectorServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DirectorServiceImpl implements DirectorServiceInterface {
    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorDto getDirectorById(Long id) {
        return directorMapper.toDto(directorRepository.findById(id).orElse(null));
    }

    public List<DirectorDto> getAllDirectors() {
        return directorMapper.toDtoList(directorRepository.findAll());
    }

    public DirectorDto addDirector(DirectorDto directorDto) {
        return directorMapper.toDto(directorRepository.save(directorMapper.toEntity(directorDto)));
    }

    public DirectorDto updateDirector(Long id, DirectorDto newDirectorDto) {
        Director director = directorRepository.findById(id).orElse(null);

        if (director == null) {
            return null;
        }

        director.setName(newDirectorDto.getNameDto());

        return directorMapper.toDto(directorRepository.save(director));
    }

    public boolean deleteDirector(Long id) {
        Director director = directorRepository.findById(id).orElse(null);

        if (director != null) {
            for (Movie movie : director.getMovies()) {
                movie.setDirector(null);
            }
            directorRepository.delete(director);
            return true;
        }

        return false;
    }

}
