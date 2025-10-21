package com.cnc.app.controller;

import com.cnc.app.model.Image;
import com.cnc.app.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class homeController {
    final private ImageController imageController;
    final private ImageService  imageService;
    public homeController(ImageController imageController, ImageService imageService) {
        this.imageController = imageController;
        this.imageService = imageService;
    }
    @GetMapping("/")
    public void home() throws IOException {
        char[][] pixels = imageService.createImage();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                System.out.println(pixels[i][j]);
            }
            System.out.println("/n");
        }
    }
}
