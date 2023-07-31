package com.endava.bugHunting.bug_hunting.controller;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities.User;
import com.endava.bugHunting.bug_hunting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public User registerUserAccount(@RequestBody UserDto userDto) {
        User save = userService.save(userDto);
        return save;
    }
}
