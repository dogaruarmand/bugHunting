package com.endava.bugHunting.bug_hunting.controller;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.exceptions.errors.EmailExistException;
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

    private static final String EMAIL_NOT_UNIQUE_MSG = "Email %s already in database";
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public UserDto registerUserAccount(@RequestBody UserDto userDto) {

        UserDto savedUser = userService.save(userDto);
        if (savedUser.getErrorMsg() != null) {
            throw new EmailExistException(savedUser.getErrorMsg());
        }
        return savedUser;
    }

}
