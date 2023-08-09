package com.endava.bugHunting.bug_hunting.controller;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.exceptions.errors.EmailExistException;
import com.endava.bugHunting.bug_hunting.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private static final String LOGGED_IN_SUCCESSFULLY_MSG = "User '%s' logged in successfully!";
    private static final String LOG_OUT_SUCCESSFULLY_MSG = "User '%s' logged out successfully!";

    @Autowired
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUsers(
            @RequestHeader(name = "email", required = true) String email) {

        List<UserDto> users = userService.getUsers(email);
        if (users.get(0).getErrorMsg() != null) {
            throw new EntityNotFoundException(users.get(0).getErrorMsg());
        }

        return users;
    }

    @PostMapping(value = "/register")
    public UserDto registerUserAccount(@RequestBody UserDto userDto) {

        UserDto savedUser = userService.save(userDto);
        if (savedUser.getErrorMsg() != null) {
            throw new EmailExistException(savedUser.getErrorMsg());
        }
        return savedUser;
    }

    @GetMapping(value = "/login")
    public String logIn(@RequestHeader(name = "email", required = true) String email,
                        @RequestHeader(name = "password", required = true) String password) {

        UserDto loggedUser = userService.logIn(email, password);
        if (loggedUser.getErrorMsg() != null) {
            throw new EntityNotFoundException(loggedUser.getErrorMsg());
        }

        return String.format(LOGGED_IN_SUCCESSFULLY_MSG, email);
    }

    @GetMapping(value = "/logout")
    public String logOut(@RequestHeader String email) {
        userService.logOut(email);

        return String.format(LOG_OUT_SUCCESSFULLY_MSG, email);
    }

    @GetMapping(value = "/{userId}")
    public UserDto getUser(@PathVariable(value = "userId") Long userId) {
        UserDto userById = userService.getById(userId);
        if (userById.getErrorMsg() != null) {
            throw new EntityNotFoundException(userById.getErrorMsg());
        }

        return userById;
    }

    @PutMapping(value = "/{userId}")
    public UserDto updateUser(@PathVariable("userId") Long userId,
                              @RequestBody UserDto userDto) {

        UserDto updatedUser = userService.update(userId, userDto);
        if (updatedUser.getErrorMsg() != null) {
            throw new EntityNotFoundException(updatedUser.getErrorMsg());
        }

        return updatedUser;
    }

    @DeleteMapping(value = "/delete/{userId}")
    public UserDto deleteUser(@PathVariable("userId") Long userId) {

        UserDto deletedUser = userService.delete(userId);

        if (deletedUser.getErrorMsg() != null) {
            throw new EntityNotFoundException(deletedUser.getErrorMsg());
        }

        return deletedUser;
    }

}
