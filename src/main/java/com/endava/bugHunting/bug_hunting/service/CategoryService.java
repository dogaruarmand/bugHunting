package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    public List<CategoryDto> findAll();

    public CategoryDto save(CategoryDto categoryDto);

    public CategoryDto findCategory(String category);
}
