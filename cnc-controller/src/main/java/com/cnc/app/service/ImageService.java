package com.cnc.app.service;

import com.cnc.app.controller.ImageController;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;


@Service
public class ImageService {

    private final PixelsProcessing pixelsProcessing;
    private final ImageController  imageController;

    public ImageService(PixelsProcessing pixelsProcessing, ImageController imageController) throws IOException {
        this.pixelsProcessing = pixelsProcessing;
        this.imageController = imageController;
    }

    public char[][] createImage() throws IOException {
        BufferedImage img = imageController.readImage();
        return pixelsProcessing.processImageToPixels(img);
    }




}
