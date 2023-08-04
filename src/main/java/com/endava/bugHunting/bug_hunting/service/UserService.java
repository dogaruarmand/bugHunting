package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);

    UserDto update(Long userId, UserDto userDto);

    UserDto logIn(UserDto userDto);

    void logOut(UserDto userDto);

    UserDto getById(Long userId);

    List<UserDto> getUsers(String email);
}
