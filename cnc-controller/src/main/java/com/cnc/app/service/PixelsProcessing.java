package com.cnc.app.service;

import com.cnc.app.model.Point;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Service
public class PixelsProcessing {

    public char[][] processImageToPixels(BufferedImage bi) {
        int[][] pixels = extractDownsampledPixels(bi);
        return pixelsToCharArray(pixels);
    }

    //helper function
    private int[][] extractDownsampledPixels(BufferedImage img) {
        int origWidth = img.getWidth();
        int origHeight = img.getHeight();

        // Calculate new small size
        double DOWNSAMPLE_SCALE = 0.2;
        int newWidth = (int) (origWidth * DOWNSAMPLE_SCALE);
        int newHeight = (int) (origHeight * DOWNSAMPLE_SCALE);

        // Create result array (your "Mat" replacement)
        int[][] pixels = new int[newHeight][newWidth];

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                // Map to original coordinates (downsampling)
                int origX = (int) (x / DOWNSAMPLE_SCALE);
                int origY = (int) (y / DOWNSAMPLE_SCALE);

                // Get original pixel
                int rgb = img.getRGB(origX, origY);

                // Fast grayscale (0-255)
                int gray = (int) (0.3 * ((rgb >> 16) & 0xFF) +
                        0.59 * ((rgb >> 8) & 0xFF) +
                        0.11 * (rgb & 0xFF));

                // Threshold: 0=black, 1=white (for CNC)
                pixels[y][x] = (gray < 128) ? 0 : 1;
            }
        }
        return pixels;
    }

    //helper function
    private char[][] pixelsToCharArray(int[][] pixels) {
        char[][] charPixels = new char[pixels.length][pixels[0].length];

        for (int y = 0; y < pixels.length; y++) {
            for (int x = 0; x < pixels[y].length; x++) {
                charPixels[y][x] = (pixels[y][x] == 0) ? '1' : ' ';
            }
        }
        return charPixels;
    }
}