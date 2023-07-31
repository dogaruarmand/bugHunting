package com.endava.bugHunting.bug_hunting.dto;

import com.endava.bugHunting.bug_hunting.constants.RolesType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RolesType rolesType;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
