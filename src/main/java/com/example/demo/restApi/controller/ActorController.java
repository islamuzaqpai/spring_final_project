package com.example.demo.restApi.controller;

import com.example.demo.restApi.dto.ActorDto;
import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.service.implementation.ActorServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
public class ActorController {
    private final ActorServiceImpl actorService;

    @GetMapping("/actors/{id}")
    public ActorDto getActorById(@PathVariable Long id) {
        return actorService.getActorById(id);
    }

    @GetMapping("/actors")
    public List<ActorDto> getAllActors() {
        return actorService.getAllActors();
    }

    @PostMapping("/actors")
    public ActorDto addActor(@RequestBody ActorDto actorDto) {
        return actorService.addActor(actorDto);
    }

    @PutMapping("/actors/{id}")
    public ActorDto updateActor(@PathVariable Long id, @RequestBody ActorDto newActorDto) {
        return actorService.updateActor(id, newActorDto);
    }

    @DeleteMapping("/actors/{id}")
    public boolean deleteActor(@PathVariable Long id) {
        return actorService.deleteActor(id);
    }
}
