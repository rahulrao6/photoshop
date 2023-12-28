package model;

import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Represents an Image which is a sequence of pixels.
 */
public class Image implements IImage {

  private Pixel[][] pixels;
  protected int height;
  protected int width;
  protected int xcoord;
  protected int ycoord;


  /**
   * Creates an image based on a 2D array of pixels.
   *
   * @param pixels represents a 2D array of pixels composing the image.
   * @throws IllegalArgumentException if the array is null.
   */

  public Image(Pixel[][] pixels) {

    Objects.requireNonNull(pixels);

    this.pixels = pixels;
    this.height = pixels[0].length;
    this.width = pixels.length;
    this.xcoord = 0;
    this.ycoord = 0;
  }


  @Override
  public Pixel[][] getArray() {

    return this.pixels;

  }

  @Override
  public Image getImage(Pixel[][] pixels) {
    return new Image(pixels);

  }

  @Override
  public Pixel getPixelAtCoord(int row, int col) {

    if (row > this.height || col > this.width) {
      throw new IndexOutOfBoundsException("Invalid coordinates");
    }

    return pixels[row][col];

  }

  @Override
  public Pixel getPixelValue(int row, int col) {

    if (row > this.height || col > this.width) {
      throw new IndexOutOfBoundsException("Invalid coordinates");
    }

    return this.pixels[row][col];

  }

  @Override
  public int getWidth() {

    return this.width;

  }

  @Override
  public int getHeight() {

    return this.height;

  }

  @Override
  public String ppmFormat(int max) {

    StringBuilder rGB = new StringBuilder();

    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (Objects.isNull(this.pixels[j][i])) {
          System.out.println("null : " + i + " " + j);
        } else {
          rGB.append(this.pixels[j][i].toString()).append(" ");
        }

      }
    }

    return "P3\n" +
            this.width + " " + this.height + "\n" +
            max + "\n" + rGB;

  }


  @Override
  public BufferedImage toBufferedImage(Image image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixelValue = image.getPixel(image, x, y);
        bufferedImage.setRGB(x, y, pixelValue);
      }
    }
    return bufferedImage;
  }

  @Override
  public int getPixel(Image image, int x, int y) {
    Pixel pixel = image.getPixelAtCoord(x, y);
    int alpha = 255;
    int red = pixel.getRed();
    int green = pixel.getGreen();
    int blue = pixel.getBlue();
    //shifts the bits accordingly (where alpha is most significant and returns)
    return (alpha << 24) | (red << 16) | (green << 8) | blue;
  }


}






