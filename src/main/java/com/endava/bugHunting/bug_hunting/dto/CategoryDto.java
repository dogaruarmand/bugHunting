package com.endava.bugHunting.bug_hunting.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
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
