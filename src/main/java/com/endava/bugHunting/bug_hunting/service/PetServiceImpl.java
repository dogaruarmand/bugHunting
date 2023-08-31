package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.*;
import com.endava.bugHunting.bug_hunting.entities.*;
import com.endava.bugHunting.bug_hunting.repository.PetRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BreedService breedService;

    @Autowired
    private LocationService locationService;


    private PetDto validate(PetDto dto) {
        if (StringUtils.isEmpty(dto.getName())) {
            dto.setError("Name should not be empty!");
            return dto;
        }

        if (StringUtils.isEmpty(dto.getName())) {
            dto.setError("Name should not be empty!");
            return dto;
        }

        if (StringUtils.isEmpty(dto.getOwnersName())) {
            dto.setError("Owner name should not be empty!");
            return dto;
        }

        if (StringUtils.isEmpty(dto.getOwnersEmail())) {
            dto.setError("Owner email should not be empty!");
            return dto;
        }

        if (StringUtils.isEmpty(dto.getOwnersPhone())) {
            dto.setError("Owner phone should not be empty!");
            return dto;
        }

        if (StringUtils.isEmpty(dto.getGender())) {
            dto.setError("Gender should not be empty!");
            return dto;
        }

        dto.setAddopted("NO");

        if (StringUtils.isEmpty(dto.getCategory())) {
            dto.setError("Category should not be empty!");
            return dto;
        } else {
            CategoryDto category = categoryService.findCategory(dto.getCategory());
            if (category.hasErrors()) {
                dto.setError(category.getError());
                return dto;
            } else {
                dto.setCategoryId(category.getId());
            }
        }

//        if (StringUtils.isEmpty(dto.getBreed())) {
//            dto.setError("Breed should not be empty!");
//            return dto;
//        } else {
//            BreedDto breed = breedService.findBreed(dto.getBreed());
//
//            if (breed.hasErrors()) {
//                dto.setError(breed.getError());
//                return dto;
//            } else {
//                dto.setBreedId(breed.getId());
//            }
//        }

        if (StringUtils.isEmpty(dto.getLocationName())) {
            dto.setError("Location name should not be empty!");
            return dto;
        } else {
            LocationDto location = locationService.findLocation(dto.getLocationName());

            if (location.hasErrors()) {
                dto.setError(location.getErrorMessage());
                return dto;
            } else {
                dto.setLocationName(location.getName());
                dto.setLocationAddress(location.getAddress());
                dto.setLocationId(location.getId());
                dto.setLocationPhoneNumber(location.getPhoneNumber());
            }
        }

        if (StringUtils.isEmpty(dto.getDescription())) {
            dto.setError("Description should not be empty");
            return dto;
        }

        if (StringUtils.isEmpty(dto.getUserEmail())) {
            dto.setError("User Email should not be empty!");
            return dto;
        } else {
            UserDto userDto = userService.findByEmail(dto.getUserEmail());

            if (userDto.hasErrors()) {
                dto.setError(userDto.getErrorMsg());
                return dto;
            } else {
                dto.setUserEmail(userDto.getEmail());
                dto.setUserName(userDto.getFirstName() + " " + userDto.getLastName());
                dto.setUserRole(userDto.getRole());
                dto.setUserId(userDto.getUserId());
            }
        }


        return dto;
    }

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
            pets = petRepository.findAllByUser(userDto.getUserId());
        }
        if (pets != null) {
            pets.stream().forEach(pet -> dtos.add(mapToDto(pet)));
        }

        return dtos;
    }

    @Override
    public PetDto save(PetDto petDto) {
        petDto = validate(petDto);
        if (petDto.hasErrors()) {
            return petDto;
        }

        Pet pet = mapToPersist(petDto);
        System.out.println("");
        pet = petRepository.save(pet);
        petDto.setId(pet.getId());

        return petDto;
    }

    private PetDto mapToDto(Pet pet) {
        PetDto petDto = PetDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .age(pet.getAge())
                .ownersName(pet.getOwnersName())
                .ownersPhone(pet.getOwnersPhone())
                .ownersEmail(pet.getOwnersEmail())
                .gender(pet.getGender())
                .addopted(pet.getAddopted())
                .categoryId(pet.getType().getId())
                .category(pet.getType().getCategory())
//                .breedId(pet.getBreed().getId())
                .breed(pet.getBreed())
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
                .image(pet.getImage())
                .build();

        if (pet.getFoster() != null) {
            petDto.setFosterId(pet.getFoster().getUserId());
            petDto.setFosterName(pet.getFoster().getLastName() + " " + pet.getFoster().getFirstName());
            petDto.setFosterEmail(pet.getFoster().getEmail());
            petDto.setFosterPhone(pet.getFoster().getPhone());
            petDto.setFosterRole((pet.getFoster().getRole()));
        }
        return petDto;
    }


    public Pet mapToPersist(PetDto dto) {
        Pet pet = Pet.builder()
                .id(dto.getId())
                .name(dto.getName())
                .age(dto.getAge())
                .ownersName(dto.getOwnersName())
                .ownersEmail(dto.getOwnersEmail())
                .ownersPhone(dto.getOwnersPhone())
                .gender(dto.getGender())
//                .foster(new User())
                .user(User.builder().userId(dto.getUserId()).build())
                .addopted(dto.getAddopted())
                .type(Category.builder().id(dto.getCategoryId()).build())
                .breed(dto.getBreed())
                .location(Location.builder().id(dto.getLocationId()).build())
                .description(dto.getDescription())
                .build();

        if(dto.getFosterId() != null) {
            pet.setFoster(User.builder().userId(dto.getFosterId()).build());
        }

        return pet;
    }

    public PetDto addoptPet(Integer petId, String email) {
        PetDto petDto = new PetDto();

        Optional<Pet> petPersist = petRepository.findById(petId);
        if (!petPersist.isPresent()) {
            petDto.setError("The pet you want to adopted don't exists!");
            return petDto;
        }

        petDto = mapToDto(petPersist.get());

        if ("YES".equalsIgnoreCase(petDto.getAddopted())) {
            petDto.setError("This pet is already adopted!");
            return petDto;
        }

        UserDto user = userService.findByEmail(email);
        if (user == null) {
            petDto.setError(String.format("The user %s don't exists!", email));
            return petDto;
        }

        if (petDto.getUserId().equals(user.getUserId())) {
            petDto.setError("Why do you want to adopt the pet you own?");
            return petDto;
        }

        if (!userService.isAdmin(petDto.getUserEmail())) {
            petDto.setError("This pet cannot be adopted cause' is not added by an admin user!");
            return petDto;
        }

        petDto.setAddopted("YES");
        petDto.setFosterId(user.getUserId());
        Pet pet = mapToPersist(petDto);
        pet.setFoster(User.builder().userId(user.getUserId()).build());
        pet = petRepository.saveAndFlush(pet);
        petDto = mapToDto(pet);

        return petDto;
    }

}