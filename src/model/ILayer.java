package model;


import java.awt.image.BufferedImage;

/**
 * A layer is the canvas for which images can be added on to. Layers can also have filters applied
 * to them by modifying the pixels on the layer.
 */

public interface ILayer {


  /**
   * performs the action of creating the filter (red) on the layer.
   */
  void redComponent();


  /**
   * performs the action of creating the filter (blue) on the layer.
   */

  void blueComponent();

  /**
   * performs the action of creating the filter (green) on the layer.
   */

  void greenComponent();

  /**
   * Does nothing to an image.
   */

  void normal();

  /**
   * performs luma function on a layer.
   *
   * @param input takes in a string which contains brighten or darken.
   */
  void luma(String input);

  /**
   * performs an intensity function on a layer.
   *
   * @param input takes in a string which contains brighten or darken.
   */

  void intensity(String input);


  /**
   * performs an intensity function on a layer.
   *
   * @param input takes in a string which contains brighten or darken.
   */

  void value(String input);

  /**
   * adds an image to a layer at a particular location..
   *
   * @param sourcePath source path of image.
   * @param xCoord     x coordinate
   * @param yCoord     y coordinate
   */
  void addImageToLayer(String sourcePath, int xCoord, int yCoord);


  /**
   * converts a Layer to a Buffered Image.
   *
   * @param layer takes in a specific ILayer that will be converted.
   * @return BufferedImage that will be shown in view.
   */
  public BufferedImage toBufferedImage(ILayer layer);

  /**
   * applies difference function on entire layer.
   *
   * @param composite entire composite image that the function is being applied to.
   */
  void difference(IImage composite);

  /**
   * applies multiply function on entire layer.
   *
   * @param composite entire composite image that the function is being applied to.
   */
  void multiply(IImage composite);

  /**
   * applies screen function on entire layer.
   *
   * @param composite entire composite image that the function is being applied to.
   */
  void screen(IImage composite);

  /**
   * gets the Pixel at a certain coordinate in the layer.
   *
   * @param x coordinate of the Pixel.
   * @param y coordinate of the Pixel.
   * @return the Pixel of the given x and y coordinate.
   */
  public Pixel getPixelatCoord(int x, int y);

  /**
   * takes the current layer and converts it into an Image.
   *
   * @return the Image object that was the converted Layer.
   */
  public Image toIm();


  /**
   * gets the Layers of the Pixel (array).
   *
   * @return a 2d array of pixels that was the current layer.
   */
  public Pixel[][] getLayers();


}
