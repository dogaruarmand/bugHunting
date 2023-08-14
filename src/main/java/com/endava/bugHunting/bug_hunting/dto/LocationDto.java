package com.endava.bugHunting.bug_hunting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {
    private Integer id;
    private String address;
    private String name;
    private String phoneNumber;
    private String errorMessage;

    public boolean hasErrors() {
        if(errorMessage == null) {
            return false;
        }
        return !StringUtils.isEmpty(errorMessage);
    }
}
