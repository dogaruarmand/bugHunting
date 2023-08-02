package com.endava.bugHunting.bug_hunting.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String errorMsg;

    public UserDto(String email, String password) {

    }

    public UserDto() {

    }
}
