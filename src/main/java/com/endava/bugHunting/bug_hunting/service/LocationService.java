package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.LocationDto;
import com.endava.bugHunting.bug_hunting.entities.Location;

import java.util.List;

public interface LocationService {

    public List<LocationDto> findAll();

    public LocationDto save(LocationDto location);
}
