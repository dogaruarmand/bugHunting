package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities.User;
import com.endava.bugHunting.bug_hunting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private final String EMAIL_NOT_UNIQUE_MSG = "Email '%s' is already in database";
    private final String EMAIL_OR_PASSWORD_NOT_FOUND = "Email '%s' not found, or password incorrect";
    private final String USER_ALREADY_LOGGED_IN_MSG = "User '%s' already logged in!";
    private final String USER_NOT_FOUND = "User with id '%s' not found!";
    private final String PERMISSIONS_DENIED_MSG = "User with email '%s', permission denied!";

    private Map<String, String> loggedUsers = new HashMap<>();

    @Override
    public UserDto save(UserDto userDto) {
        Boolean emailPresent = isEmailPresent(userDto);
        if (emailPresent) {
            userDto.setErrorMsg(String.format(EMAIL_NOT_UNIQUE_MSG, userDto.getEmail()));
            return userDto;
        }

        User user = new User(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhone(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole());

        User savedUser = userRepository.save(user);
        userDto.setUserId(savedUser.getUserId());

        return userDto;
    }

    @Override
    public UserDto update(Long userId, UserDto userDto) {
        Boolean emailPresent = isEmailPresent(userDto);
        if (emailPresent) {
            userDto.setErrorMsg(String.format(EMAIL_NOT_UNIQUE_MSG, userDto.getEmail()));
            return userDto;
        }

        if (userRepository.findById(userId).isPresent()) {
            User toBeSaved = userRepository.findById(userId).get();

            toBeSaved.setFirstName(userDto.getFirstName());
            toBeSaved.setLastName(userDto.getLastName());
            toBeSaved.setPhone(userDto.getPhone());
            toBeSaved.setEmail(userDto.getEmail());
            toBeSaved.setPassword(userDto.getPassword());

            User updatedUser = userRepository.save(toBeSaved);
            userDto.setUserId(updatedUser.getUserId());
            userDto.setRole(updatedUser.getRole());
        }

        return userDto;
    }

    @Override
    public UserDto delete(Long userId) {

        UserDto deletedUser = new UserDto();
        if (userRepository.findById(userId).isPresent()) {

            User toBeDeleted = userRepository.findById(userId).get();
            deletedUser.setUserId(toBeDeleted.getUserId());
            deletedUser.setEmail(toBeDeleted.getEmail());

            userRepository.deleteById(userId);
        } else {
            deletedUser.setErrorMsg(String.format(USER_NOT_FOUND, userId));
        }

        return deletedUser;
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

    @Override
    public void logOut(UserDto userDto) {
        if (userAlreadyLogged(userDto)) {
            loggedUsers.remove(userDto.getEmail());
        }
    }

    @Override
    public UserDto getById(Long userId) {
        Optional<User> userById = getUserById(userId);
        UserDto userDto = new UserDto();
        if (userById.isEmpty()) {
            userDto.setErrorMsg(String.format(USER_NOT_FOUND, userId));
            return userDto;
        }

        mapToDto(userById, userDto);
        return userDto;
    }

    @Override
    public List<UserDto> getUsers(String email) {

        List<UserDto> usersDto = new ArrayList<>();

        if (isAdmin(email)) {
            List<User> allUsers = userRepository.findAll();

            for (User user : allUsers) {
                usersDto.add(mapToUsersDto(user));
            }
        } else {
            usersDto.add(mapToUsersDtoError(email));
        }

        return usersDto;
    }

    public boolean isAdmin(String email) {

        boolean admin = false;
        for (Map.Entry<String, String> loggedUser : loggedUsers.entrySet()) {
            admin = loggedUser.getKey().equals(email) && loggedUser.getValue().equals("ADMIN");
            if (admin) break;
        }

        return admin;
    }

    private UserDto mapToUsersDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }

    private UserDto mapToUsersDtoError(String email) {
        UserDto userDto = new UserDto();
        userDto.setErrorMsg(String.format(PERMISSIONS_DENIED_MSG, email));
        return userDto;
    }

    private void mapToDto(Optional<User> userById, UserDto userDto) {
        userDto.setUserId(userById.get().getUserId());
        userDto.setFirstName(userById.get().getFirstName());
        userDto.setLastName(userById.get().getLastName());
        userDto.setPhone(userById.get().getPhone());
        userDto.setEmail(userById.get().getEmail());
        userDto.setPassword(userById.get().getPassword());
        userDto.setRole(userById.get().getRole());
    }

    public boolean userAlreadyLogged(UserDto userDto) {
        return loggedUsers.containsKey(userDto.getEmail());
    }

    public boolean userAlreadyLogged(String email) {
        return loggedUsers.containsKey(email);
    }

    @Override
    public UserDto findByEmail(String email) {
        List<User> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            return null;
        }
        return mapToUsersDto(users.get(0));
    }

    private Boolean isEmailPresent(UserDto userDto) {
        List<User> user = userRepository.findByEmail(userDto.getEmail());
        return user.size() > 0;
    }

    private Optional<User> userExist(UserDto userDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        return user;
    }

    private Optional<User> getUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user;
    }
}
