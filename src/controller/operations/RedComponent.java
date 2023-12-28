package controller.operations;

import model.ILayer;

/**
 * This class creates a red filter on the layer.
 */
public class RedComponent implements Operations {
  protected String layerName;

  /**
   * Constructor for red Component.
   *
   * @param layerName name of layer to be executed on.
   */
  public RedComponent(String layerName) {
    this.layerName = layerName;

  }


  /**
   * executes the red component on the specific layer.
   *
   * @param layer represents an instance of ILayer.
   */
  @Override
  public void execute(ILayer layer) {
    layer.redComponent();


  }
}
