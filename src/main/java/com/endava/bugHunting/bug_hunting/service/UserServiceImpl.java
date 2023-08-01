package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities.User;
import com.endava.bugHunting.bug_hunting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static final String EMAIL_NOT_UNIQUE_MSG = "Email '%s' is already in database";

    @Override
    public UserDto save(UserDto userDto) {
        Boolean emailPresent = isEmailPresent(userDto);
        if (emailPresent) {
            userDto.setErrorMsg(String.format(EMAIL_NOT_UNIQUE_MSG, userDto.getEmail()));
            return userDto;
        }

        User user = new User(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole());

        User savedUser = userRepository.save(user);
        userDto.setUserId(savedUser.getUserId());

        return userDto;
    }

    private Boolean isEmailPresent(UserDto userDto) {
        List<User> user = userRepository.findByEmail(userDto.getEmail());
        return user.size() > 0;
    }
}
