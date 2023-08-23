package com.endava.bugHunting.bug_hunting.service2;

import com.endava.bugHunting.bug_hunting.dto.BreedDto;
import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import com.endava.bugHunting.bug_hunting.entities2.Breed2;
import com.endava.bugHunting.bug_hunting.entities2.Category2;
import com.endava.bugHunting.bug_hunting.repository2.Breed2Repository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Breed2ServiceImpl implements Breed2Service {

    @Autowired
    private Breed2Repository breedRepository;

    @Autowired
    private Category2Service categoryService;

    @Override
    public List<BreedDto> findAll() {
        List<BreedDto> dtos = new ArrayList<>();
        List<Breed2> allBreeds = breedRepository.findAll();
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

        Breed2 breed = Breed2.builder()
                .id(dto.getId())
                .category(Category2.builder()
                        .id(dto.getIdCategory())
                        .category(dto.getCategory())
                        .build())
                .breed(dto.getBreed())
                .build();

        breed = breedRepository.save(breed);
        dto.setId(breed.getId());
        return dto;
    }

    @Override
    public BreedDto findBreed(String breed) {
        BreedDto dto = new BreedDto();
        Breed2 persist = breedRepository.findBreedByBreed(breed);

        if(persist == null) {
            dto.setError(String.format("Breed %s don't exist!", breed));
        } else {
            dto = dto.builder()
                    .id(persist.getId())
                    .category(persist.getBreed())
                    .build();
        }

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
