package com.endava.bugHunting.bug_hunting.service2;

import com.endava.bugHunting.bug_hunting.dto.BreedDto;

import java.util.List;
public interface Breed2Service {

    public List<BreedDto> findAll();

    public BreedDto save(BreedDto breed);

    public BreedDto findBreed(String breed);
}
