package controller.operations;

import model.ILayer;

/**
 * This class creates a blue filter on the layer.
 */
public class BlueComponent implements Operations {

  /**
   * constructs a BlueComponent.
   *
   * @param layerName name of the Layer.
   */
  public BlueComponent(String layerName) {
    String lay;
    lay = layerName;

  }

  /**
   * takes in a layers object, and executes a specific operation upon it.
   *
   * @param layer represents an instance of ILayer.
   */
  @Override
  public void execute(ILayer layer) {

    layer.blueComponent();

  }


}
