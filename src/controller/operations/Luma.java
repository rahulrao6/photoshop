package controller.operations;

import model.ILayer;

/**
 * This class creates a luma filter on the layer that is selected.
 */
public class Luma implements Operations {


  protected String input;

  /**
   * Constructor for Luma.
   *
   * @param input     represents whether this is to be darkened or lightened/
   * @param layerName represents the layer this is being applied upon.
   */
  public Luma(String input, String layerName) {
    this.input = input;
    String lay;
    lay = layerName;
  }


  /**
   * executes the green component on the specific layer.
   *
   * @param layer represents an instance of ILayer.
   */
  @Override
  public void execute(ILayer layer) {
    layer.luma(input);


  }
}
