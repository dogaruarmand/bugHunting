package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities.User;
import com.endava.bugHunting.bug_hunting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole());

        return userRepository.save(user);
    }
}
