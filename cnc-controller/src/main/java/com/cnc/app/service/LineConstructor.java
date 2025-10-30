package com.cnc.app.service;

import com.cnc.app.model.Line;
import com.cnc.app.model.Point;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Service
public class LineConstructor {
    private final PixelsProcessing pixelsProcessing;

    public LineConstructor(PixelsProcessing pixelsProcessing) {
        this.pixelsProcessing = pixelsProcessing;
    }

    public ArrayList<Line> createLines(BufferedImage img) {
        ArrayList<Line> lines = new ArrayList<>();

        return lines;
    }

    //helper function
    private ArrayList<Point> shortenedPoints(BufferedImage img, ArrayList<Point> points)  {
        ArrayList<Point> shortenedPointsList = new ArrayList<>();
        shortenedPointsList.add(points.get(0));
        for (int i = 1; i < points.size(); i++) {

        }

        return shortenedPointsList;
    }
    //helper function
    private ArrayList<Point> points(BufferedImage img){
        ArrayList<Point> points = new ArrayList<>();
        char[][] pixels = pixelsProcessing.processImageToPixels(img);
        for(int i = 0; i < pixels.length; i++){
            for(int j = 0; j < pixels[0].length; j++){
                if(pixels[i][j] == '1'){
                    points.add(new Point(i, j));
                }
            }
        }
        return points;
    }
}
