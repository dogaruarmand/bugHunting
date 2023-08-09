package com.endava.bugHunting.bug_hunting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BreedDto {

    private Integer id;
    private Integer idCategory;
    private String category;
    private String breed;
    private String error;

    public boolean hasErrors() {
        if(error == null) {
            return false;
        }
        return !StringUtils.isEmpty(error);
    }
}
