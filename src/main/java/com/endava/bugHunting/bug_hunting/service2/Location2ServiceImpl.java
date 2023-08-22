package com.endava.bugHunting.bug_hunting.service2;

import com.endava.bugHunting.bug_hunting.dto.LocationDto;
import com.endava.bugHunting.bug_hunting.entities2.Location2;
import com.endava.bugHunting.bug_hunting.repository2.Location2Repository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Location2ServiceImpl implements Location2Service {

    @Autowired
    private Location2Repository locationRepository;

    @Autowired
    private User2Service userService;

    @Override
    public List<LocationDto> findAll() {
        List<Location2> list = locationRepository.findAll();
        List<LocationDto> locations = new ArrayList<>();
        if (list != null || !list.isEmpty()) {
            list.stream().forEach(location -> locations.add(LocationDto.builder()
                    .id(location.getId())
                    .address(location.getAddress())
                    .name(location.getName())
                    .phoneNumber(location.getPhoneNumber())
                    .build()));
        }
        return locations;
    }

    private LocationDto validate(LocationDto location) {

        if (StringUtils.isEmpty(location.getAddress())) {
            location.setErrorMessage("Address cannot be null!");
            return location;
        }
        if (StringUtils.isEmpty(location.getName())) {
            location.setErrorMessage("Name cannot be null!");
            return location;
        }

        if(locationRepository.findLocationByName(location.getName()) != null) {
            location.setErrorMessage("The name of the current location already exist!");
            return location;
        }

        if (StringUtils.isEmpty(location.getPhoneNumber())) {
            location.setErrorMessage("Phone number cannot be null!");
            return location;
        }
        return location;
    }

    @Override
    public LocationDto save(LocationDto location) {
        location = validate(location);

        if(!StringUtils.isEmpty(location.getErrorMessage())) {
            return location;
        }

        Location2 loc = locationRepository.save(Location2.builder()
                .address(location.getAddress())
                .name(location.getName())
                .phoneNumber(location.getPhoneNumber())
                .build());
        location.setId(loc.getId());
        return location;
    }

    @Override
    public LocationDto findLocation(String name) {
        LocationDto dto = new LocationDto();
        Location2 persist = locationRepository.findLocationByName(name);

        if(persist == null) {
            dto.setErrorMessage(String.format("Breed %s don't exist!", name));
        } else {
            dto = dto.builder()
                    .id(persist.getId())
                    .phoneNumber(persist.getPhoneNumber())
                    .name(persist.getName())
                    .address(persist.getAddress())
                    .build();
        }

        return dto;
    }

}
