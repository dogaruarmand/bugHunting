package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.UserDto;

public interface UserService {
    UserDto save(UserDto userDto);

    UserDto logIn(UserDto userDto);

}
