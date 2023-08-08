package com.endava.bugHunting.bug_hunting.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDto {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String role;
    private String errorMsg;

    public UserDto(String email, String password) {

    }

    public UserDto() {

    }
}
