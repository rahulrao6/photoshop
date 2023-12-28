package model;

/**
 * Represents the interface of a Pixel. Which has an R, G, B, and Alpha value ranging from
 * 0-255.
 */

public interface IPixel {

  /**
   * gets the red component of the pixel.
   *
   * @return integer of red component.
   */
  public int getRed();

  /**
   * gets the blue component of the pixel.
   *
   * @return integer of blue component.
   */
  public int getBlue();

  /**
   * gets the green component of the pixel.
   *
   * @return integer of green component.
   */
  public int getGreen();


  /**
   * Gets the alpha value.
   *
   * @return the alpha value of the pixel
   */

  public int getAlpha();


  /**
   * Sets the pixels red component.
   *
   * @param value the value to be set to.
   */

  public void setRed(int value);

  /**
   * Sets the pixels green component.
   *
   * @param value the value to be set to.
   */

  public void setGreen(int value);


  /**
   * Sets the pixels blue component.
   *
   * @param value the value to be set to.
   */

  public void setBlue(int value);


  /**
   * Sets the pixels alpha component.
   *
   * @param value the value to be set to.
   */

  public void setAlpha(int value);


  /**
   * This represents the max value of rgb.
   *
   * @return This returns an int.
   */

  public int value();


  /**
   * This method represents the intensity of the pixel.
   *
   * @return This returns an int.
   */

  public int intensity();


  /**
   * This returns the weighted sum of Luma.
   *
   * @return This returns an int.
   */


  public int luma();


  /**
   * applies the multiply algorithim on a single pair of pixels.
   *
   * @param other other pixel value.
   * @return the pixel value, after operations were conducted to the pair of pixels.
   */
  public Pixel multiply(IPixel other);


  /**
   * applies the screen algorithim on a single pair of pixels.
   *
   * @param other other pixel value.
   * @return the pixel value, after operations were conducted to the pair of pixels.
   */
  public Pixel screen(IPixel other);

  /**
   * applies the difference algorithim on a single pair of pixels.
   *
   * @param other other pixel value.
   * @return the pixel value, after operations were conducted to the pair of pixels.
   */
  public Pixel difference(IPixel other);


  /**
   * converts an RGB value to an HSL value taking in the RGB values.
   *
   * @param r red component value.
   * @param g green component value.
   * @param b blue component value.
   * @return
   */
  public HSL convertRGBtoHSL(double r, double g, double b);

  /**
   * converts an HSL value to an RGB value taking in the HSL values.
   *
   * @param hue        hue value of HSL.
   * @param saturation saturation value of HSL.
   * @param lightness  lightness component of HSL.
   */
  public void convertHSLtoRGB(double hue, double saturation, double lightness);

  /**
   * gets the Pixel value from the particular HSL value.
   *
   * @param hue        hue value of HSL.
   * @param saturation saturation value of HSL.
   * @param lightness  lightness component of HSL.
   * @return a Pixel with the converted HSL value back to RGB.
   */
  public Pixel getPixelfromHSL(double hue, double saturation, double lightness);


}
