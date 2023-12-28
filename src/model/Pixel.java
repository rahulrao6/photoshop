package model;

import java.util.Objects;

/**
 * Represents each pixel in the image which contains an RGB component. This class implements IPixel
 * which is the interface representation of Pixel.
 */
public class Pixel implements IPixel {

  private int blue;
  private int red;
  private int green;
  private int alpha;

  /**
   * creates a fully opaque pixel with given RGB component values.
   *
   * @param red   is the blue value of the pixel.
   * @param blue  is the red value of the pixel.
   * @param green is the green value of the pixel.
   * @throws IllegalArgumentException if any value is less than 0 or greater than 255.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {

    if ((blue < 0 || red < 0 || green < 0) || (blue > 255 || red > 255 || green > 255)) {
      throw new IllegalArgumentException("Invalid RGB value");
    }

    this.blue = blue;
    this.red = red;
    this.green = green;
    this.alpha = 255;


  }

  /**
   * Creates a pixel given an alpha value, converts it from 4 component to 3 component.
   *
   * @param blue  is the blue value of the pixel.
   * @param red   is the red value of the pixel.
   * @param green is the green value of the pixel.
   * @param alpha is the alpha value of the pixel.
   * @throws IllegalArgumentException if any value is less than 0 or greater than 255.
   */

  public Pixel(int red, int green, int blue, int alpha) {

    if ((blue < 0 || red < 0 || green < 0 || alpha < 0) ||
            (blue > 255 || red > 255 || green > 255 || alpha > 255)) {
      throw new IllegalArgumentException("Invalid RGBA value");
    }

    this.alpha = alpha;
    this.red = red;
    this.blue = blue;
    this.green = green;

  }


  @Override
  public int getRed() {

    return this.red;
  }

  @Override
  public int getBlue() {

    return this.blue;
  }


  @Override
  public int getGreen() {

    return this.green;
  }

  @Override
  public int getAlpha() {

    return this.alpha;
  }

  @Override
  public void setRed(int value) {

    this.red = value;


  }

  @Override
  public void setGreen(int value) {

    this.green = value;

  }

  @Override
  public void setBlue(int value) {

    this.blue = value;

  }

  @Override
  public void setAlpha(int value) {


    this.alpha = value;

  }

  /**
   * returns the values of the pixel component as RGBA values in String format.
   *
   * @return String of pixel component.
   */
  @Override
  public String toString() {

    return this.red + " " + this.green + " " + this.blue;
  }


  @Override
  public boolean equals(Object o) {

    if (o instanceof Pixel) {

      Pixel that = (Pixel) o;

      return this.red == that.red &&
              this.green == that.green &&
              this.blue == that.blue &&
              this.alpha == that.alpha;

    } else {
      return false;
    }
  }


  @Override
  public int hashCode() {

    return Objects.hash(this.red, this.green, this.blue, this.alpha);


  }

  @Override
  public int value() {

    return Math.max((Math.max(this.red, this.green)), this.blue);
  }

  @Override
  public int intensity() {
    // need an integer so rounding should provide a reasonable output
    return (int) Math.round((double) (red + green + blue) / 3);
  }

  @Override
  public int luma() {
    // luma double gets rounded to a long and then to an integer

    return Math.toIntExact(Math.round(0.2126 * red + 0.7152 * green + 0.0722 * blue));
  }

  @Override
  public Pixel difference(IPixel pix) {

    int newRed = Math.abs(this.red - pix.getRed());
    int newBlue = Math.abs(this.blue - pix.getBlue());
    int newGreen = Math.abs(this.green - pix.getGreen());

    return new Pixel(newRed, newGreen, newBlue, this.alpha);
  }

  @Override
  public Pixel multiply(IPixel pix) {
    HSL current = convertRGBtoHSL(this.red, this.green, this.blue);
    HSL other = convertRGBtoHSL(pix.getRed(), pix.getGreen(), pix.getBlue());

    //gets new Light by multiplying
    double newLight = current.getLight() * other.getLight();

    Pixel pixel = getPixelfromHSL(current.getHue(), current.getSaturation(), newLight);
    pixel.setAlpha(this.getAlpha());
    return pixel;

  }

  @Override
  public Pixel screen(IPixel pix) {
    HSL current = convertRGBtoHSL(this.red, this.green, this.blue);
    HSL other = convertRGBtoHSL(pix.getRed(), pix.getGreen(), pix.getBlue());

    //gets new Light by multiplying
    double newLight = 1 - ((1 - current.getLight()) * (1 - other.getLight()));

    Pixel pixel = getPixelfromHSL(current.getHue(), current.getSaturation(), newLight);
    pixel.setAlpha(this.getAlpha());
    return pixel;

  }

  @Override
  public HSL convertRGBtoHSL(double r, double g, double b) {
    r = r / 255;
    g = g / 255;
    b = b / 255;

    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue;
    double saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        while (hue < 0) {
          hue += 6; //hue must be positive to find the appropriate modulus
        }
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }
    return new HSL(hue, saturation, lightness);

  }

  @Override
  public void convertHSLtoRGB(double hue, double saturation, double lightness) {
    double r = convertFn(hue, saturation, lightness, 0) * 255;
    double g = convertFn(hue, saturation, lightness, 8) * 255;
    double b = convertFn(hue, saturation, lightness, 4) * 255;

    setRed((int) r);
    setBlue((int) b);
    setGreen((int) g);
  }

  @Override
  public Pixel getPixelfromHSL(double hue, double saturation, double lightness) {
    double r = convertFn(hue, saturation, lightness, 0) * 255;
    double g = convertFn(hue, saturation, lightness, 8) * 255;
    double b = convertFn(hue, saturation, lightness, 4) * 255;

    Pixel pixel = new Pixel((int) r, (int) g, (int) b);
    return pixel;

  }

  /*
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model
   */
  private static double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }
}
