package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.Image;
import model.Pixel;


/**
 * This class contains utility methods in order to read and save an Image. This is in order
 * to remove this functionality from other areas of the code, and will be used from the ImageUtil
 * class.
 */
public class ImageUtil {
  Pixel[][] pixels;


  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public Image readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return new Image(pixels);
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);

    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file: " + maxValue);

    pixels = new Pixel[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixels[j][i] = new Pixel(r, g, b);
      }
    }
    return new Image(pixels);
  }

  /**
   * method that can take a fileName and read an Image through the use of ImageIO libraries
   * and BufferImages.
   *
   * @param fileName name of file (sourcePath) for the Image.
   * @return an Image.
   */
  public Image readImage(String fileName) {
    BufferedImage buffIm;
    try {
      buffIm = ImageIO.read(new File(fileName));
    } catch (IOException e) {
      System.out.println("File not found");
      return new Image(pixels);
    }
    int width = buffIm.getWidth();
    int height = buffIm.getHeight();
    pixels = new Pixel[width][height];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int rgba = buffIm.getRGB(i, j);
        //
        int a = (rgba >> 24) & 0xFF;
        int r = (rgba >> 16) & 0xFF;
        int g = (rgba >> 8) & 0xFF;
        int b = rgba & 0xFF;
        pixels[j][i] = new Pixel(r, g, b, a);

      }

    }
    return new Image(pixels);

  }


  /**
   * saves an Image given the Name of the image and the sourcepath.
   *
   * @param input      name of the image.
   * @param sourcepath sourcePath of the image.
   */
  public static void saveImage(String input, String sourcepath) {

    FileWriter writer = null;
    try {
      writer = new FileWriter(sourcepath);

    } catch (IOException e) {
      System.err.println(e.getMessage());

    }
    if (writer != null) {
      try {
        writer.write(input);
        writer.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * saves other conventional types of images.
   *
   * @param image    image of image type.
   * @param fileName name of the file to be saved.
   * @param format   format of the file and what type of extension.
   */
  public static void saveAllImage(Image image, String fileName, String format) {


    try {
      BufferedImage im = image.toBufferedImage(image);
      File outputfile = new File(fileName);
      ImageIO.write(im, format, outputfile);
    } catch (IOException e) {
      System.out.println("Did not print" + e.getMessage());
    }
  }


}

