package com.endava.bugHunting.bug_hunting.service2;

import com.endava.bugHunting.bug_hunting.dto.CategoryDto;

import java.util.List;

public interface Category2Service {

    public List<CategoryDto> findAll();

    public CategoryDto save(CategoryDto categoryDto);

    public CategoryDto findCategory(String category);
}
