package com.endava.bugHunting.bug_hunting.controller2;

import com.endava.bugHunting.bug_hunting.dto.BreedDto;
import com.endava.bugHunting.bug_hunting.exceptions.errors.EmailExistException;
import com.endava.bugHunting.bug_hunting.service2.Breed2Service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/breeds")
public class Breed2Controller {

    @Autowired
    private Breed2Service breedService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BreedDto> getBreeds() {
        List<BreedDto> breeds = breedService.findAll();
        if (breeds.isEmpty()) {
            throw new EntityNotFoundException("No breeds were found");
        }

        return breeds;
    }

    @PostMapping(value = "/save")
    public BreedDto save(@RequestBody BreedDto dto) {
        dto = breedService.save(dto);
        if(dto.hasErrors()) {
            throw new EmailExistException(dto.getError());
        }
        return dto;
    }
}
