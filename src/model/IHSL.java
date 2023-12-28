package model;

/**
 * Interface for the HSL class. This interface contains getters and setters for this class.
 * The HSL implementation differs from the Pixel in that it can measure Light, and then
 * later convert back into the Pixel RGB.
 */
public interface IHSL {

  /**
   * gets the Hue of the HSL component.
   *
   * @return the Hue of the current HSL object.
   */
  public double getHue();

  /**
   * gets the Saturation of the HSL component.
   *
   * @return the Saturation of the current HSL object.
   */
  public double getSaturation();

  /**
   * gets the Light of the HSL component.
   *
   * @return the Light of the current HSL object.
   */
  public double getLight();


  /**
   * sets the hue of the HSL component based on the parameter.
   *
   * @param hue value that is passed in.
   */
  public void setHue(double hue);

  /**
   * sets the saturation of the HSL component based on the parameter.
   *
   * @param saturation value that is passed in.
   */
  public void setSaturation(double saturation);

  /**
   * sets the light of the HSL component based on the parameter.
   *
   * @param light value that is passed in.
   */
  public void setLight(double light);


}
