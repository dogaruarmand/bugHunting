package com.endava.bugHunting.bug_hunting.controller;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
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
//        Boolean emailPresent = isEmailPresent(userDto);
//        if(emailPresent){
//            throw new IllegalStateException(String.format(EMAIL_NOT_UNIQUE_MSG, userDto.getEmail()));
//        }
        UserDto savedUser = userService.save(userDto);
        if (savedUser.getErrorMsg() != null) {
            throw new IllegalStateException(savedUser.getErrorMsg());
        }
        return savedUser;
    }

//    private Boolean isEmailPresent(UserDto userDto) {
//        List<User> user = userService.findByEmail(userDto.getEmail());
//        return user.size() > 0;
//    }
}
