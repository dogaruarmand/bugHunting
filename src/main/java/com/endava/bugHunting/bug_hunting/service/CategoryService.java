package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    public List<CategoryDto> findAll();

    public CategoryDto save(CategoryDto categoryDto, MultipartFile file) throws IOException;

    public CategoryDto findCategory(String category);
}
