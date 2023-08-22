package com.endava.bugHunting.bug_hunting.service2;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities2.User2;
import com.endava.bugHunting.bug_hunting.repository2.User2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class User2ServiceImpl implements User2Service {
    @Autowired
    private User2Repository userRepository;

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

        User2 user = new User2(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhone(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole());

        User2 savedUser = userRepository.save(user);
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
            User2 toBeSaved = userRepository.findById(userId).get();

            toBeSaved.setFirstName(userDto.getFirstName());
            toBeSaved.setLastName(userDto.getLastName());
            toBeSaved.setPhone(userDto.getPhone());
            toBeSaved.setEmail(userDto.getEmail());
            toBeSaved.setPassword(userDto.getPassword());

            User2 updatedUser = userRepository.save(toBeSaved);
            userDto.setUserId(updatedUser.getUserId());
            userDto.setRole(updatedUser.getRole());
        }

        return userDto;
    }

    @Override
    public UserDto delete(Long userId) {

        UserDto deletedUser = new UserDto();
        if (userRepository.findById(userId).isPresent()) {

            User2 toBeDeleted = userRepository.findById(userId).get();
            deletedUser.setUserId(toBeDeleted.getUserId());
            deletedUser.setEmail(toBeDeleted.getEmail());

            userRepository.deleteById(userId);
        } else {
            deletedUser.setErrorMsg(String.format(USER_NOT_FOUND, userId));
        }

        return deletedUser;
    }

    @Override
    public UserDto logIn(String email, String password) {
        Optional<User2> loggedUser = userExist(email, password);
        if (loggedUser.isEmpty()) {
            String errorMsg = String.format(EMAIL_OR_PASSWORD_NOT_FOUND, email);
            return mapToUsersDtoError(errorMsg);
        }

        if (userAlreadyLogged(email)) {
            String errorMsg = String.format(USER_ALREADY_LOGGED_IN_MSG, email);
            return mapToUsersDtoError(errorMsg);
        }

        loggedUsers.put(email, loggedUser.get().getRole());
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setRole(loggedUser.get().getRole());

        return userDto;
    }

    @Override
    public void logOut(String email) {
        if (userAlreadyLogged(email)) {
            loggedUsers.remove(email);
        }
    }

    @Override
    public UserDto getById(Long userId) {
        Optional<User2> userById = getUserById(userId);
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
            List<User2> allUsers = userRepository.findAll();

            for (User2 user : allUsers) {
                usersDto.add(mapToUsersDto(user));
            }
        } else {
            String errorMsg = String.format(PERMISSIONS_DENIED_MSG, email);
            usersDto.add(mapToUsersDtoError(errorMsg));
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

    private UserDto mapToUsersDto(User2 user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());

        return userDto;
    }

    private UserDto mapToUsersDtoError(String errorMsg) {
        UserDto userDto = new UserDto();
        userDto.setErrorMsg(errorMsg);

        return userDto;
    }

    private void mapToDto(Optional<User2> userById, UserDto userDto) {
        userDto.setUserId(userById.get().getUserId());
        userDto.setFirstName(userById.get().getFirstName());
        userDto.setLastName(userById.get().getLastName());
        userDto.setPhone(userById.get().getPhone());
        userDto.setEmail(userById.get().getEmail());
        userDto.setPassword(userById.get().getPassword());
        userDto.setRole(userById.get().getRole());
    }

    public boolean userAlreadyLogged(String email) {
        return loggedUsers.containsKey(email);
    }

    @Override
    public UserDto findByEmail(String email) {
        List<User2> users = userRepository.findByEmail(email);
        if (users.isEmpty()) {
            return null;
        }

        return mapToUsersDto(users.get(0));
    }

    private Boolean isEmailPresent(UserDto userDto) {
        List<User2> user = userRepository.findByEmail(userDto.getEmail());

        return user.size() > 0;
    }

    private Optional<User2> userExist(String email, String password) {
        Optional<User2> user = userRepository.findByEmailAndPassword(email, password);

        return user;
    }

    private Optional<User2> getUserById(Long userId) {
        Optional<User2> user = userRepository.findById(userId);

        return user;
    }
}
