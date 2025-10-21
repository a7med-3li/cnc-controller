package com.cnc.app.service;

import com.cnc.app.controller.ImageController;
import com.cnc.app.model.Image;
import com.cnc.app.model.Point;
import org.opencv.core.Mat;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        Mat bufferedImageToMat = pixelsProcessing.bufferedImageToMat(img);
        Mat scaledImage = pixelsProcessing.skeletonize(bufferedImageToMat);
        return pixelsProcessing.extractPixels(scaledImage);
    }




}
