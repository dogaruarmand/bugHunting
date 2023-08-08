package com.endava.bugHunting.bug_hunting.controller;

import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import com.endava.bugHunting.bug_hunting.dto.PetDto;
import com.endava.bugHunting.bug_hunting.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
