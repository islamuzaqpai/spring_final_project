package com.example.demo.test.service;

import com.example.demo.restApi.dto.ActorDto;
import com.example.demo.restApi.service.implementation.ActorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class ActorServiceTest {

    @Autowired
    private ActorServiceImpl actorService;

    @Test
    void getActorByIdTest() {
        Random random = new Random();

        int randomIndex = random.nextInt(actorService.getAllActors().size());

        Long someIndex = actorService.getAllActors().get(randomIndex).getId();

        ActorDto actorDto = actorService.getActorById(someIndex);
        Assertions.assertNotNull(actorDto);
        Assertions.assertNotNull(actorDto.getId());
        Assertions.assertNotNull(actorDto.getNameDto());

        ActorDto check = actorService.getActorById(-1L);

        Assertions.assertNull(check);
    }

    @Test
    void getAllActorsTest() {

        List<ActorDto> actorsDto = actorService.getAllActors();

        Assertions.assertNotEquals(0, actorsDto.size());
        Assertions.assertNotNull(actorsDto);

        for (ActorDto actorDto : actorsDto) {
            Assertions.assertNotNull(actorDto);
            Assertions.assertNotNull(actorDto.getId());
            Assertions.assertNotNull(actorDto.getNameDto());
        }
    }

    @Test
    void addActorTest() {

        ActorDto actorDto = new ActorDto(1L, "First", null);

        ActorDto add = actorService.addActor(actorDto);

        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getNameDto());

        Assertions.assertEquals(add.getId(), actorDto.getId());
        Assertions.assertEquals(add.getNameDto(), actorDto.getNameDto());

        ActorDto added = actorService.getActorById(add.getId());

        Assertions.assertEquals(add.getId(), added.getId());
        Assertions.assertEquals(add.getNameDto(), added.getNameDto());
    }

    @Test
    void updateDirectorTest() {

        Random random = new Random();

        int randomIndex = random.nextInt(actorService.getAllActors().size());

        Long someIndex = actorService.getAllActors().get(randomIndex).getId();

        ActorDto actorDto = new ActorDto(someIndex, "Update", null);

        ActorDto update = actorService.updateActor(someIndex, actorDto);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getNameDto());


        Assertions.assertEquals(update.getId(), actorDto.getId());
        Assertions.assertEquals(update.getNameDto(), actorDto.getNameDto());

        ActorDto updated = actorService.getActorById(someIndex);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getNameDto());


        Assertions.assertEquals(update.getId(), updated.getId());
        Assertions.assertEquals(update.getNameDto(), updated.getNameDto());
    }

    @Test
    void deleteActorTest() {

        Random random = new Random();

        int randomIndex = random.nextInt(actorService.getAllActors().size());

        Long someIndex = actorService.getAllActors().get(randomIndex).getId();

        Assertions.assertTrue(actorService.deleteActor(someIndex));

        ActorDto check = actorService.getActorById(someIndex);
        Assertions.assertNull(check);
    }

}
