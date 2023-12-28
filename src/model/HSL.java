package model;

/**
 * Hue Saturation and Light Implementation, works to convert with Pixel: RGB implementation.
 * This implementation is used in order to measure light/brightness which we cannot do in the other
 * Pixel class.
 */
public class HSL implements IHSL {
  private double hue;
  private double saturation;
  private double light;


  /**
   * HSL constructor to construct an HSL object with the given parameters.
   *
   * @param hue        sets the hue component as long as it is between 0 & 360.
   * @param saturation sets the saturation component as long as it is between 0 and 1.
   * @param light      sets the light component as long as it is between 0 and 1 (inclusive).
   */
  public HSL(double hue, double saturation, double light) {
    if (hue >= 0 && hue < 360) {
      this.hue = hue;
    }
    if (saturation >= 0 && saturation < 1) {
      this.saturation = saturation;
    }
    if (light >= 0 && light <= 1) {
      this.light = light;
    } else {
      throw new IllegalArgumentException("Invalid HSL Values");
    }
  }

  @Override
  public double getHue() {
    return this.hue;
  }

  @Override
  public double getSaturation() {
    return this.saturation;
  }

  @Override
  public double getLight() {
    return this.light;
  }

  @Override
  public void setHue(double hue) {
    this.hue = hue;
  }

  @Override
  public void setSaturation(double saturation) {
    this.saturation = saturation;
  }

  @Override
  public void setLight(double light) {
    this.light = light;
  }

  @Override
  public String toString() {
    return this.hue + " " + this.saturation + " " + this.light;
  }

}
