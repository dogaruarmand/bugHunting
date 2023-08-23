package com.endava.bugHunting.bug_hunting.service2;

import com.endava.bugHunting.bug_hunting.dto.LocationDto;

import java.util.List;

public interface Location2Service {

    public List<LocationDto> findAll();

    public LocationDto save(LocationDto location);

    public LocationDto findLocation(String name);
}
