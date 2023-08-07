package com.endava.bugHunting.bug_hunting.controller;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.exceptions.errors.EmailExistException;
import com.endava.bugHunting.bug_hunting.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class LoginController {

    private static final String LOGGED_IN_SUCCESSFULLY_MSG = "User '%s' logged in successfully!";
    private static final String LOG_OUT_SUCCESSFULLY_MSG = "User '%s' logged out successfully!";

    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public String logIn(@RequestBody UserDto userDto) {
        UserDto loggedUser = userService.logIn(userDto);
        if (loggedUser.getErrorMsg() != null) {
            throw new EntityNotFoundException(loggedUser.getErrorMsg());
        }

        return String.format(LOGGED_IN_SUCCESSFULLY_MSG, userDto.getEmail());
    }

    @GetMapping(value = "/logout")
    public String logOut(@RequestBody UserDto userDto) {
        userService.logOut(userDto);
        return String.format(LOG_OUT_SUCCESSFULLY_MSG, userDto.getEmail());
    }

    @PostMapping(value = "/register")
    public UserDto registerUserAccount(@RequestBody UserDto userDto) {

        UserDto savedUser = userService.save(userDto);
        if (savedUser.getErrorMsg() != null) {
            throw new EmailExistException(savedUser.getErrorMsg());
        }
        return savedUser;
    }
}
