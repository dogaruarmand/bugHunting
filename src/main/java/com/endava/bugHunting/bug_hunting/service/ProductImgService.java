package com.endava.bugHunting.bug_hunting.service;

import com.endava.bugHunting.bug_hunting.entities.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductImgService {

    public ProductImage uploadImage(MultipartFile file) throws IOException;

    public byte[] downloadImage(String fileName) throws IOException;
}
