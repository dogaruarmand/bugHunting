package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.dto.CategoryDto;
import com.endava.bugHunting.bug_hunting.entities.Category;
import com.endava.bugHunting.bug_hunting.entities.ProductImage;
import com.endava.bugHunting.bug_hunting.repository.CategoryRepository;
import com.endava.bugHunting.bug_hunting.repository.ProductImageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductImageRepository imageRepo;

    private final Path tempDirWithPrefix = Files.createTempDirectory("img");
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd-mm-ss");

    public CategoryServiceImpl() throws IOException {
    }

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
    public CategoryDto save(CategoryDto categoryDto, MultipartFile file) throws IOException {
        categoryDto = validate(categoryDto);
        if(categoryDto.hasErrors()) {
            return categoryDto;
        }

        Category category = Category.builder()
                .category(categoryDto.getCategory())
                .imageName(getFileName(file.getOriginalFilename()))
                .imageType(file.getContentType())
                .imagePath(tempDirWithPrefix.toString())
                .build();

        category = categoryRepository.save(category);
        categoryDto.setId(category.getId());
        // save image
//        ProductImage pImage = new ProductImage();
//        pImage.setName(file.getOriginalFilename());
//        pImage.setType(file.getContentType());
//        pImage.setImagePath(tempDirWithPrefix.toString());

        file.transferTo(new File(tempDirWithPrefix.toUri()));

        return categoryDto;
    }

    @Override
    public CategoryDto findCategory(String category) {
        CategoryDto dto = new CategoryDto();
        Category persist =categoryRepository.findCategoryByCategory(category);

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

        Category category = categoryRepository.findCategoryByCatgoryLowerCase(categoryDto.getCategory());
        if (category != null) {
            categoryDto.setError("The current category already exists!");
        }

        return categoryDto;
    }

    private String getFileName(String imageType) {
        OffsetDateTime currentDateTime = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC);
        StringBuilder fileName = new StringBuilder(DATE_FORMATTER.format(currentDateTime));
        fileName.append(".");
        fileName.append(imageType);
        return fileName.toString();
    }
}
