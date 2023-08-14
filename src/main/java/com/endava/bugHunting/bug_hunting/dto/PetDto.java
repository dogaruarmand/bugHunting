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
public class PetDto {

    private Integer id;
    private String name;
    private Integer age;
    private String ownersName;
    private String ownersPhone;
    private String ownersEmail;
    private String gender;
    private String addopted; //yes/no
    private Integer fosterId;
    private String fosterName;
    private String fosterEmail;
    private String fosterPhone;
    private String fosterRole;
    private Integer categoryId;
    private String category;
    private Integer breedId;
    private String breed;
    private Integer locationId;
    private String locationAddress;
    private String locationName;
    private String locationPhoneNumber;
    private String description;
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userRole;
    private String error;

    public boolean hasErrors() {
        if(error == null) {
            return false;
        }
        return !StringUtils.isEmpty(error);
    }
}
