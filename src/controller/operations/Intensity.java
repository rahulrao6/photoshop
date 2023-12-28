package controller.operations;

import model.ILayer;

/**
 * This class creates a Intensity filter on the layer. It works with the text-based program.
 */
public class Intensity implements Operations {

  private String input;

  /**
   * Represents the Constructor for the Intensity component.
   *
   * @param input     of whether we are darkening or lightening.
   * @param layerName the layer to be applied on.
   */
  public Intensity(String input, String layerName) {
    this.input = input;
    String lay;
    lay = layerName;

  }


  /**
   * executes the intensity component on the specific layer.
   *
   * @param layer represents an instance of ILayer.
   */
  @Override
  public void execute(ILayer layer) {
    layer.intensity(input);


  }
}
