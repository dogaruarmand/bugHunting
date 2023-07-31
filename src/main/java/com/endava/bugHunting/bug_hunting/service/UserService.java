package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities.User;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);

//    List<User> findByEmail(String email);
}
