package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.PetDto;

import java.util.List;

public interface PetService {

    public List<PetDto> findMyPets(String user);
}
