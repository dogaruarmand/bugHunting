package com.endava.bugHunting.bug_hunting.service2;

import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import com.endava.bugHunting.bug_hunting.entities2.Category2;
import com.endava.bugHunting.bug_hunting.repository2.Category2Repository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Category2ServiceImpl implements Category2Service {

    @Autowired
    private Category2Repository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryDto> dtos = new ArrayList<>();
        List<Category2> categories = categoryRepository.findAll();
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

        Category2 category = Category2.builder()
                .category(categoryDto.getCategory())
                .build();

        category = categoryRepository.save(category);
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    @Override
    public CategoryDto findCategory(String category) {
        CategoryDto dto = new CategoryDto();
        Category2 persist =categoryRepository.findCategoryByCategory(category);

        if(persist == null) {
            dto.setError(String.format("Category %s don't exist!", category));
        } else {
            dto = dto.builder()
                    .id(persist.getId())
                    .category(persist.getCategory())
                    .build();
        }

        return dto;
    }

    private CategoryDto validate(CategoryDto categoryDto) {
        if (StringUtils.isEmpty(categoryDto.getCategory())) {
            categoryDto.setError("The category cannot be null!");
            return categoryDto;
        }

        Category2 category = categoryRepository.findCategoryByCatgoryLowerCase(categoryDto.getCategory());
        if (category != null) {
            categoryDto.setError("The current category already exists!");
        }

        return categoryDto;
    }
}
