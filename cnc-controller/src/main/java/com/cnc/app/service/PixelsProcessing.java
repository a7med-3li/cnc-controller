package com.cnc.app.service;

import com.cnc.app.model.Point;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class PixelsProcessing {

    public Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC1);
        for (int y = 0; y < bi.getHeight(); y++) {
            for (int x = 0; x < bi.getWidth(); x++) {
                int rgb = bi.getRGB(x, y);
                int gray = (int) (0.3 * ((rgb >> 16) & 0xFF) +
                        0.59 * ((rgb >> 8) & 0xFF) +
                        0.11 * (rgb & 0xFF));
                mat.put(y, x, gray);
            }
        }
        return mat;
    }

    public ArrayList<Point> extractBlackPixels(Mat mat) {
        ArrayList<Point> blackPixels = new ArrayList<>();

        for (int y = 0; y < mat.rows(); y++) {
            for (int x = 0; x < mat.cols(); x++) {
                double[] pixel = mat.get(y, x);  // get pixel value

                if (pixel != null && pixel.length > 0 && pixel[0] == 0.0) {
                    blackPixels.add(new Point(x, y));
                }
            }
        }

        return blackPixels;
    }

    public Mat skeletonize(Mat src) {
        Mat skel = Mat.zeros(src.size(), CvType.CV_8UC1);
        Mat temp = new Mat();
        Mat eroded = new Mat();

        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_CROSS, new Size(3, 3));

        boolean done;
        do {
            Imgproc.erode(src, eroded, element);
            Imgproc.dilate(eroded, temp, element);
            Core.subtract(src, temp, temp);
            Core.bitwise_or(skel, temp, skel);
            eroded.copyTo(src);

            done = Core.countNonZero(src) == 0;
        } while (!done);

        return skel;
    }

    public char[][] extractPixels(Mat mat) {
        ArrayList<Point> blackPixels = extractBlackPixels(mat);

        char[][] pixels = new char[mat.height()][mat.width()];

        // Fill all with '0'
        for (int y = 0; y < mat.height(); y++) {
            Arrays.fill(pixels[y], '0');
        }
        for (int y = 0; y < mat.width(); y++) {
            Arrays.fill(pixels[y], '0');
        }

        // Set black pixels
        for (Point p : blackPixels) {
            int x = (int) p.getX();
            int y = (int) p.getY();
            if (x >= 0 && x < mat.width() && y >= 0 && y < mat.height()) {
                pixels[y][x] = '1';
            }
        }

        return pixels;
    }



}
