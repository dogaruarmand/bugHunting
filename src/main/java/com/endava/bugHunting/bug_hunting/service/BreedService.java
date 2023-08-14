package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.BreedDto;

import java.util.List;
public interface BreedService {

    public List<BreedDto> findAll();

    public BreedDto save(BreedDto breed);

    public BreedDto findBreed(String breed);
}
