package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.entities.ProductImage;
import com.endava.bugHunting.bug_hunting.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class ProductImgServiceImpl implements ProductImgService {

    @Autowired
    private ProductImageRepository imageRepo;

    private final String PATH = "C:\\workspace\\";
    private final Path tempDirWithPrefix = Files.createTempDirectory("img");


    public void ProductImageServiceImpl() throws IOException {
    }

    public ProductImgServiceImpl() throws IOException {
    }

    @Override
    public ProductImage uploadImage(MultipartFile file) throws IOException {
//        String fullPath = PATH+file.getOriginalFilename();
        ProductImage pImage = new ProductImage();
        pImage.setName(file.getOriginalFilename());
        pImage.setType(file.getContentType());
//        pImage.setImagePath(fullPath);
        pImage.setImagePath(tempDirWithPrefix.toString());

//        file.transferTo(new File(fullPath));
        file.transferTo(new File(tempDirWithPrefix.toUri()));
        return imageRepo.save(pImage);
    }

    @Override
    public byte[] downloadImage(String fileName) throws IOException {
        Optional<ProductImage> imageObject = imageRepo.findByName(fileName);
        String fullPath = imageObject.get().getImagePath();
        return Files.readAllBytes(new File(fullPath).toPath());
    }
}
