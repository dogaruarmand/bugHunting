package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.PetDto;
import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities.Pet;
import com.endava.bugHunting.bug_hunting.repository.PetRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserService userService;

    public List<PetDto> findMyPets(String email) {
        List<PetDto> dtos = new ArrayList<>();

        if (StringUtils.isEmpty(email)) {
            return dtos;
        }

        UserDto userDto = userService.findByEmail(email);
        if (userDto == null) {
            return dtos;
        }

        List<Pet> pets = null;
        if (userService.isAdmin(email)) {
            pets = petRepository.findAll();
        } else {
            pets = petRepository.findAllByFoster(userDto.getUserId());
        }
        if (pets != null) {
            pets.stream().forEach(pet -> dtos.add(mapToDto(pet)));
        }

        return dtos;
    }

    private PetDto mapToDto(Pet pet) {
        return PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .ownersName(pet.getOwnersName())
                .ownersPhone(pet.getOwnersPhone())
                .ownersEmail(pet.getOwnersEmail())
                .gender(pet.getGender())
                .addopted(pet.getAddopted())
                .fosterId(pet.getFoster().getUserId())
                .fosterName(pet.getFoster().getLastName() + " " + pet.getFoster().getFirstName())
                .fosterEmail(pet.getFoster().getEmail())
                .fosterPhone(pet.getFoster().getPhone())
                .fosterRole((pet.getFoster().getRole()))
                .categoryId(pet.getType().getId())
                .category(pet.getType().getCategory())
                .breedId(pet.getBreed().getId())
                .breed(pet.getBreed().getBreed())
                .locationId(pet.getLocation().getId())
                .locationAddress(pet.getLocation().getAddress())
                .locationName(pet.getLocation().getName())
                .locationPhoneNumber(pet.getLocation().getPhoneNumber())
                .description(pet.getDescription())
                .userId(pet.getUser().getUserId())
                .userPhone(pet.getUser().getPhone())
                .userEmail(pet.getUser().getEmail())
                .userRole(pet.getUser().getRole())
                .userName(pet.getUser().getFirstName() + " " + pet.getUser().getLastName())
                .build();
    }

}