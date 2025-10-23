package com.cnc.app.controller;

import com.cnc.app.model.Image;
import com.cnc.app.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

@RestController
public class homeController {
    final private ImageController imageController;
    final private ImageService  imageService;
    public homeController(ImageController imageController, ImageService imageService) {
        this.imageController = imageController;
        this.imageService = imageService;
    }
    @GetMapping("/")
    public String home() throws IOException {
        return "Mission accomplished!" ;
    }

    @GetMapping("/pixels")
    public ResponseEntity<String> getPixels() throws IOException {
        char[][] pixels = imageService.createImage();
        return ResponseEntity.ok(Arrays.deepToString(pixels));
    }
}
