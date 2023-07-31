package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.constants.RolesType;
import com.endava.bugHunting.bug_hunting.dto.UserDto;
import com.endava.bugHunting.bug_hunting.entities.User;
import com.endava.bugHunting.bug_hunting.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RolesType rolesType;

    @Override
    public User save(UserDto userDto) {
//        User user = new User(userDto.getFirstName(),
//                userDto.getLastName(),
//                userDto.getEmail(),
//                userDto.getPassword(),
//                List.of(userDto.getRolesType()),
//                        userDto.getCreatedAt(),
//                        userDto.getUpdatedAt());
        User user = new User(
                1,
                "",
                "",
                "",
                "",
                null,
                null,
                null
        );

        return userRepository.save(user);
    }
}
