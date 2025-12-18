package com.example.demo.restApi.service;

import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.entity.Director;

import java.util.List;

public interface DirectorServiceInterface {
    DirectorDto getDirectorById(Long id);

    List<DirectorDto> getAllDirectors();

    DirectorDto addDirector(DirectorDto directorDto);

    DirectorDto updateDirector(Long id, DirectorDto newDirectorDto);

    boolean deleteDirector(Long id);
}
