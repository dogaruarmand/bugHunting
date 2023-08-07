package com.endava.bugHunting.bug_hunting.controller;

import com.endava.bugHunting.bug_hunting.dto.LocationDto;
import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.exceptions.errors.EmailExistException;
import com.endava.bugHunting.bug_hunting.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/location")
public class LocationController {

    private LocationService locationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LocationDto> getLocations() {
        List<LocationDto> locations = locationService.findAll();
        if (locations.isEmpty()) {
            throw new EntityNotFoundException("No locations were found");
        }

        return locations;
    }

    @PostMapping(value = "/save")
    public LocationDto save(@RequestBody LocationDto locationDto) {
        locationDto = locationService.save(locationDto);
        if(!StringUtils.isEmpty(locationDto.getErrorMessage())) {
            throw new EmailExistException(locationDto.getErrorMessage());
        }
        return locationDto;
    }
}
