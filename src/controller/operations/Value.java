package controller.operations;

import model.ILayer;

/**
 * This class creates a value filter on the layer.
 */
public class Value implements Operations {

  private String input;

  /**
   * Value constructor.
   *
   * @param input     for value.
   * @param layerName input for the name of the current layer.
   */
  public Value(String input, String layerName) {
    this.input = input;
    String lay;
    lay = layerName;
  }


  /**
   * executes the value component on the specific layer.
   *
   * @param layer represents an instance of ILayer.
   */
  @Override
  public void execute(ILayer layer) {
    layer.value(input);


  }
}
