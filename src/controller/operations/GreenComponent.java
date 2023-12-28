package controller.operations;

import model.ILayer;

/**
 * This class creates a green filter on the layer.
 */
public class GreenComponent implements Operations {

  /**
   * Constructs a Green component anf passes a given name.
   *
   * @param layerName name of the Layer to be applied upon.
   */
  public GreenComponent(String layerName) {
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
    layer.greenComponent();

  }
}
