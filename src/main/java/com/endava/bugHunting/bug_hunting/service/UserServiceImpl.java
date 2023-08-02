package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities.User;
import com.endava.bugHunting.bug_hunting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static final String EMAIL_NOT_UNIQUE_MSG = "Email '%s' is already in database";
    private static final String EMAIL_OR_PASSWORD_NOT_FOUND = "Email '%s' not found, or password incorrect";
    private static final String USER_ALREADY_LOGGED_IN_MSG = "User '%s' already logged in!";
    private static Map<String, String> loggedUsers = new HashMap<>();

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

    @Override
    public UserDto logIn(UserDto userDto) {
        Optional<User> loggedUser = userExist(userDto);
        if (loggedUser.isEmpty()) {
            userDto.setErrorMsg(String.format(EMAIL_OR_PASSWORD_NOT_FOUND, userDto.getEmail()));
            return userDto;
        }

        if (userAlreadyLogged(userDto)) {
            userDto.setErrorMsg(String.format(USER_ALREADY_LOGGED_IN_MSG, userDto.getEmail()));
            return userDto;
        }

        loggedUsers.put(userDto.getEmail(), loggedUser.get().getRole());
        userDto.setRole(loggedUser.get().getRole());

        return userDto;
    }

    private static boolean userAlreadyLogged(UserDto userDto) {
        return loggedUsers.containsKey(userDto.getEmail());
    }

    private Boolean isEmailPresent(UserDto userDto) {
        List<User> user = userRepository.findByEmail(userDto.getEmail());
        return user.size() > 0;
    }

    private Optional<User> userExist(UserDto userDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        return user;
    }
}
