package com.example.demo.test.service;

import com.example.demo.restApi.dto.DirectorDto;
import com.example.demo.restApi.service.implementation.DirectorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class DirectorServiceTest {

    @Autowired
    private DirectorServiceImpl directorService;

    @Test
    void getDirectorByIdTest() {
        Random random = new Random();

        int randomIndex = random.nextInt(directorService.getAllDirectors().size());

        Long someIndex = directorService.getAllDirectors().get(randomIndex).getId();

        DirectorDto directorDto = directorService.getDirectorById(someIndex);
        Assertions.assertNotNull(directorDto);
        Assertions.assertNotNull(directorDto.getId());
        Assertions.assertNotNull(directorDto.getNameDto());

        DirectorDto check = directorService.getDirectorById(-1L);

        Assertions.assertNull(check);
    }

    @Test
    void getAllDirectorsTest() {

        List<DirectorDto> directorsDto = directorService.getAllDirectors();

        Assertions.assertEquals(0, directorsDto.size());
        Assertions.assertNotNull(directorsDto);

        for (DirectorDto directorDto : directorsDto) {
            Assertions.assertNotNull(directorDto);
            Assertions.assertNotNull(directorDto.getId());
            Assertions.assertNotNull(directorDto.getNameDto());
        }
    }

    @Test
    void addDirectorTest() {

        DirectorDto directorDto = new DirectorDto(1L, "First", null);

        DirectorDto add = directorService.addDirector(directorDto);

        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getNameDto());

        Assertions.assertEquals(add.getId(), directorDto.getId());
        Assertions.assertEquals(add.getNameDto(), directorDto.getNameDto());

        DirectorDto added = directorService.getDirectorById(add.getId());

        Assertions.assertEquals(add.getId(), added.getId());
        Assertions.assertEquals(add.getNameDto(), added.getNameDto());
    }

    @Test
    void updateDirectorTest() {

        Random random = new Random();

        int randomIndex = random.nextInt(directorService.getAllDirectors().size());

        Long someIndex = directorService.getAllDirectors().get(randomIndex).getId();

        DirectorDto directorDto = new DirectorDto(someIndex, "Update", null);

        DirectorDto update = directorService.updateDirector(someIndex, directorDto);

        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getNameDto());


        Assertions.assertEquals(update.getId(), directorDto.getId());
        Assertions.assertEquals(update.getNameDto(), directorDto.getNameDto());

        DirectorDto updated = directorService.getDirectorById(someIndex);

        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getNameDto());


        Assertions.assertEquals(update.getId(), updated.getId());
        Assertions.assertEquals(update.getNameDto(), updated.getNameDto());
    }

    @Test
    void deleteDirectorTest() {

        Random random = new Random();

        int randomIndex = random.nextInt(directorService.getAllDirectors().size());

        Long someIndex = directorService.getAllDirectors().get(randomIndex).getId();

        Assertions.assertTrue(directorService.deleteDirector(someIndex));

        DirectorDto check = directorService.getDirectorById(someIndex);
        Assertions.assertNull(check);
    }
}
