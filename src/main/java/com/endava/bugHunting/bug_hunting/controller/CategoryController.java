package com.endava.bugHunting.bug_hunting.controller;


import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import com.endava.bugHunting.bug_hunting.dto.LocationDto;
import com.endava.bugHunting.bug_hunting.exceptions.errors.EmailExistException;
import com.endava.bugHunting.bug_hunting.service.CategoryService;
import com.endava.bugHunting.bug_hunting.service.LocationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            throw new EntityNotFoundException("No categories were found");
        }

        return categories;
    }

    @PostMapping(value = "/save")
    public CategoryDto save(@RequestBody CategoryDto categoryDto,
                            @RequestParam("productImage") MultipartFile file) throws IOException {
        categoryDto = categoryService.save(categoryDto, file);
        if(categoryDto.hasErrors()) {
            throw new EmailExistException(categoryDto.getError());
        }
        return categoryDto;
    }

}
