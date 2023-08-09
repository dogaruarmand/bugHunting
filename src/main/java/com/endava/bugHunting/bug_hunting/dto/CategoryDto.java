package com.endava.bugHunting.bug_hunting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;
    private String category;
    private String error;

    public boolean hasErrors() {
        if(error == null) {
            return false;
        }
        return !StringUtils.isEmpty(error);
    }

}
