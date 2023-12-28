package model;


import java.awt.image.BufferedImage;

import util.ImageUtil;


/**
 * An implementation of an ILayer that uses a 2D array of pixels to represent the "canvas" for
 * which images can be placed on. Filters can also be applied.
 */

public class ImageLayer implements ILayer {


  private Pixel[][] layers;
  private int layer_height;
  private int layer_width;


  /**
   * A constructor for an ImageLayer that only takes in a 2D array of pixels representing the layer.
   * The height and with are dependent on the array given.
   *
   * @param image The given array to be the layer
   */

  public ImageLayer(Image image) {
    this.layers = image.getArray();
    this.layer_height = layers.length;
    this.layer_width = layers[0].length;


  }

  /**
   * adds an image to a layer at a particular location.
   *
   * @param sourcePath source path of image.
   * @param xCoord     x coordinate.
   * @param yCoord     y coordinate.
   */
  @Override
  public void addImageToLayer(String sourcePath, int xCoord, int yCoord) {
    // load an image from ImageUtil class with name, source path as given in parameter
    ImageUtil util = new ImageUtil();
    String ext = sourcePath.substring(sourcePath.lastIndexOf(".") + 1).
            toLowerCase();
    Image image;
    if (ext.equals("ppm")) {
      image = util.readPPM(sourcePath);
    } else {
      image = util.readImage(sourcePath);
    }
    image.xcoord = xCoord;
    image.ycoord = yCoord;

    for (int i = 0; i < image.width; i++) {
      for (int j = 0; j < image.height; j++) {

        if (i + xCoord >= this.layer_width || j + yCoord >= this.layer_height) {
          // Skip it
        } else {
          this.layers[i + xCoord][j + yCoord] = image.getArray()[i][j];
        }
      }
    }
  }


  @Override
  public void redComponent() {
    for (int row = 0; row < this.layer_height; row++) {
      for (int column = 0; column < this.layer_width; column++) {
        Pixel pixel = this.layers[row][column];
        // int r = pixel.getRed();
        pixel.setGreen(0);
        pixel.setBlue(0);

      }
    }
  }

  @Override
  public void blueComponent() {
    for (int row = 0; row < layer_height; row++) {
      for (int column = 0; column < layer_width; column++) {
        Pixel pixel = layers[row][column];
        int b = pixel.getBlue();
        pixel.setGreen(0);
        pixel.setRed(0);

      }
    }
  }

  @Override
  public void greenComponent() {
    for (int row = 0; row < layer_height; row++) {
      for (int column = 0; column < layer_width; column++) {
        Pixel pixel = layers[row][column];
        int g = pixel.getGreen();
        pixel.setRed(0);
        pixel.setBlue(0);

      }
    }

  }


  @Override
  public void normal() {
    this.layers = layers;

  }


  @Override
  public void value(String input) {

    for (int row = 0; row < layer_height; row++) {
      for (int column = 0; column < layer_width; column++) {

        Pixel pixel = layers[row][column];

        int inc = pixel.value();

        if (input.equals("brighten")) {
          pixel.setRed(Math.min(pixel.getRed() + inc, 255));

          pixel.setGreen(Math.min(pixel.getGreen() + inc, 255));

          pixel.setBlue(Math.min(pixel.getBlue() + inc, 255));

        } else if (input.equals("darken")) {
          pixel.setRed(Math.max(pixel.getRed() - inc, 0));

          pixel.setGreen(Math.max(pixel.getGreen() - inc, 0));

          pixel.setBlue(Math.max(pixel.getBlue() - inc, 0));
        }
      }
    }
  }


  @Override
  public void intensity(String input) {


    for (int row = 0; row < this.layer_height; row++) {
      for (int column = 0; column < this.layer_width; column++) {


        Pixel pixel = layers[row][column];

        int inc = pixel.intensity();

        if (input.equals("brighten")) {
          pixel.setRed(Math.min(pixel.getRed() + inc, 255));

          pixel.setGreen(Math.min(pixel.getGreen() + inc, 255));

          pixel.setBlue(Math.min(pixel.getBlue() + inc, 255));

        } else if (input.equals("darken")) {
          pixel.setRed(Math.max(pixel.getRed() - inc, 0));

          pixel.setGreen(Math.max(pixel.getGreen() - inc, 0));

          pixel.setBlue(Math.max(pixel.getBlue() - inc, 0));
        }
      }
    }
  }

  @Override
  public void luma(String input) {

    for (int row = 0; row < this.layer_height; row++) {
      for (int column = 0; column < this.layer_width; column++) {

        Pixel pixel = layers[row][column];

        int inc = pixel.luma();

        if (input.equals("brighten")) {
          pixel.setRed(Math.min(pixel.getRed() + inc, 255));

          pixel.setGreen(Math.min(pixel.getGreen() + inc, 255));

          pixel.setBlue(Math.min(pixel.getBlue() + inc, 255));

        } else if (input.equals("darken")) {
          pixel.setRed(Math.max(pixel.getRed() - inc, 0));

          pixel.setGreen(Math.max(pixel.getGreen() - inc, 0));

          pixel.setBlue(Math.max(pixel.getBlue() - inc, 0));
        }
      }
    }
  }

  @Override
  public Image toIm() {
    Pixel[][] pixel = this.layers;
    return new Image(pixel);

  }

  @Override
  public BufferedImage toBufferedImage(ILayer layer) {
    Image image = toIm();
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
  public void difference(IImage composite) {
    for (int i = 0; i < layer_width; i++) {
      for (int j = 0; j < layer_height; j++) {
        Pixel currP = this.layers[i][j];
        Pixel underP = composite.getPixelAtCoord(i, j);

        Pixel blend = currP.difference(underP);

        layers[i][j] = blend;
      }
    }
  }

  @Override
  public void multiply(IImage image) {
    for (int i = 0; i < layer_width; i++) {
      for (int j = 0; j < layer_height; j++) {
        Pixel currP = layers[i][j];
        Pixel underP = image.getPixelAtCoord(i, j);

        Pixel blend = currP.multiply(underP);
        layers[i][j] = blend;

      }
    }
  }

  @Override
  public void screen(IImage image) {
    for (int i = 0; i < layer_width; i++) {
      for (int j = 0; j < layer_height; j++) {
        Pixel currP = layers[i][j];
        Pixel underP = image.getPixelAtCoord(i, j);
        Pixel blend = currP.screen(underP);
        layers[i][j] = blend;
      }
    }
  }

  @Override
  public Pixel[][] getLayers() {
    return this.layers;
  }

  @Override
  public Pixel getPixelatCoord(int x, int y) {
    return this.layers[x][y];

  }

}
