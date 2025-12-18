package com.example.demo.restApi.controller;

import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.service.implementation.DirectorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class DirectorController {

    private final DirectorServiceImpl directorService;

    @GetMapping("/directors/{id}")
    public DirectorDto getDirectorById(@PathVariable Long id) {
        return directorService.getDirectorById(id);
    }

    @GetMapping("/directors")
    public List<DirectorDto> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @PostMapping("/directors")
    public DirectorDto addDirector(@RequestBody DirectorDto directorDto) {
        return directorService.addDirector(directorDto);
    }

    @PutMapping("/directors/{id}")
    public DirectorDto updateDirector(@PathVariable Long id, @RequestBody DirectorDto newDirectorDto) {
        return directorService.updateDirector(id, newDirectorDto);
    }

    @DeleteMapping("/directors/{id}")
    public boolean deleteDirector(@PathVariable Long id) {
        return directorService.deleteDirector(id);
    }
}
