package com.endava.bugHunting.bug_hunting.dto;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

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

    public boolean hasErrors() {
        if(errorMsg == null) {
            return false;
        }
        return !StringUtils.isEmpty(errorMsg);
    }
}
