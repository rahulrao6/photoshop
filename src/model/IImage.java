package model;

import java.awt.image.BufferedImage;

/**
 * Represents a collection of pixels which form an image.
 */
public interface IImage {


  /**
   * Gets the image.
   *
   * @return the image as a 2D array of pixels.
   */


  Pixel[][] getArray();


  /**
   * gets the Pixel value at a particular row, col value.
   *
   * @param row the row in the array.
   * @param col the column in the array.
   * @throws IndexOutOfBoundsException if the row or column is not within the image.
   */


  Pixel getPixelAtCoord(int row, int col);

  /**
   * Returns the width of the image.
   */

  int getWidth();


  /**
   * Returns the height of the image.
   */

  int getHeight();


  /**
   * Converts an image so a ppm can read it.
   *
   * @param max the maximum RGB value in the PPM.
   * @return the image as a string that a ppm can interpret.
   */

  String ppmFormat(int max);


  /**
   * returns an Image from an array of pixels.
   *
   * @param pixels 2d array of pixels.
   * @return Image (wrapped 2d array of pixels)
   */
  public Image getImage(Pixel[][] pixels);


  /**
   * gets the Pixel value at a certain row and col val.
   *
   * @param row value, x coordinate.
   * @param col value, y coordinate.
   * @return the Pixel component at a particular location.
   */
  public Pixel getPixelValue(int row, int col);


  /**
   * takes in an Image and then converts it into a BufferedImage so it can be shown in the view.
   *
   * @param image object, 2d array of pixels.
   * @return the BufferedImage object of the Image (converted).
   */
  public BufferedImage toBufferedImage(Image image);


  /**
   * gets the pixel value of a particular image based on x and y coordinate.
   *
   * @param image that will be used to find pixel value.
   * @param x     coordinate.
   * @param y     coordinate.
   * @return returns the bit integer value of the Pixel as helper.
   */
  public int getPixel(Image image, int x, int y);


}
