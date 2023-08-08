package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import com.endava.bugHunting.bug_hunting.entities.Category;
import com.endava.bugHunting.bug_hunting.repository.CategoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryDto> dtos = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        categories.stream().forEach(category -> dtos.add(CategoryDto.builder()
                .id(category.getId())
                .category(category.getCategory())
                .build()));
        return dtos;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        categoryDto = validate(categoryDto);
        if(categoryDto.hasErrors()) {
            return categoryDto;
        }

        Category category = Category.builder()
                .category(categoryDto.getCategory())
                .build();

        category = categoryRepository.save(category);
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    private CategoryDto validate(CategoryDto categoryDto) {
        if (StringUtils.isEmpty(categoryDto.getCategory())) {
            categoryDto.setError("The category cannot be null!");
            return categoryDto;
        }

        Category category = categoryRepository.findCategoryByCatgory(categoryDto.getCategory());
        if (category != null) {
            categoryDto.setError("The current category already exists!");
        }

        return categoryDto;
    }
}
