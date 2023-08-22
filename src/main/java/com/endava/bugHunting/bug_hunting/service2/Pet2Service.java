package com.endava.bugHunting.bug_hunting.service2;

import com.endava.bugHunting.bug_hunting.dto.PetDto;

import java.util.List;

public interface Pet2Service {

    public List<PetDto> findMyPets(String user);

    public PetDto save(PetDto pet);

    public PetDto addoptPet(Integer petId, String email);
}
