package com.endava.bugHunting.bug_hunting.controller;

import com.endava.bugHunting.bug_hunting.dto.PetDto;
import com.endava.bugHunting.bug_hunting.exceptions.errors.EmailExistException;
import com.endava.bugHunting.bug_hunting.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<PetDto> getPets(@RequestHeader String email) {
        List<PetDto> pets = petService.findMyPets(email);
        if (pets.isEmpty()) {
            throw new EntityNotFoundException("No pets were found");
        }

        return pets;
    }

    @PostMapping(value = "/adoptPet")
    public PetDto adoptPet(@RequestHeader Integer petId, @RequestHeader String email) {
        PetDto pet = petService.addoptPet(petId, email);
        if(pet.hasErrors()) {
            throw new EntityNotFoundException(pet.getError());
        }
        return pet;
    }

    @PostMapping(value = "/save")
    public PetDto save(@RequestBody PetDto petDto) {
        petDto = petService.save(petDto);
        if(petDto.hasErrors()) {
            throw new EmailExistException(petDto.getError());
        }
        return petDto;
    }

}
