package com.cnc.app.controller;

import com.cnc.app.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Service
public class ImageController {
    public BufferedImage readImage() throws IOException {
        BufferedImage img;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("assets/img.png")) {
            if (is == null) {
                throw new FileNotFoundException("Image not found in resources/assets/img.png");
            }
            img = ImageIO.read(is);
        }
        return img;
    }
}