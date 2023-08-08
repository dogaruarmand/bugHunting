package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.BreedDto;
import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import com.endava.bugHunting.bug_hunting.entities.Breed;
import com.endava.bugHunting.bug_hunting.entities.Category;
import com.endava.bugHunting.bug_hunting.repository.BreedRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<BreedDto> findAll() {
        List<BreedDto> dtos = new ArrayList<>();
        List<Breed> allBreeds = breedRepository.findAll();
        allBreeds.stream().forEach(breed -> dtos.add(BreedDto.builder()
                .id(breed.getId())
                .idCategory(breed.getCategory().getId())
                .category(breed.getCategory().getCategory())
                .breed(breed.getBreed())
                .build()));

        return dtos;
    }

    @Override
    public BreedDto save(BreedDto dto) {
        dto = validate(dto);
        if (dto.hasErrors()) {
            return dto;
        }

        Breed breed = Breed.builder()
                .id(dto.getId())
                .category(Category.builder()
                        .id(dto.getIdCategory())
                        .category(dto.getCategory())
                        .build())
                .breed(dto.getBreed())
                .build();

        breed = breedRepository.save(breed);
        dto.setId(breed.getId());
        return dto;
    }

    private BreedDto validate(BreedDto dto) {
        if (StringUtils.isEmpty(dto.getBreed())) {
            dto.setError("Breed must not be null!");
            return dto;
        }

        if (StringUtils.isEmpty(dto.getCategory())) {
            dto.setError("Category must not be null!");
            return dto;
        }

        if (breedRepository.findBreedByBreed(dto.getBreed()) != null) {
            dto.setError(String.format("Breed %s is already defined!", dto.getBreed()));
            return dto;
        }

        CategoryDto categoryDto = categoryService.findCategory(dto.getCategory());
        if (categoryDto.hasErrors()) {
            dto.setError(categoryDto.getError());
        } else {
            dto.setIdCategory(categoryDto.getId());
        }

        return dto;
    }
}
