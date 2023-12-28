package controller.operations;

import model.IImage;
import model.ILayer;

/**
 * This class represents the Multiply Filter in the Operations Class.
 */
public class Multiply implements Operations {


  private IImage composite;

  /**
   * Constructor for the Multiply class.
   *
   * @param layerName takes in the name of the layer.
   * @param composite also takes in the Composite Image.
   */
  public Multiply(String layerName, IImage composite) {
    String lay;
    lay = layerName;
    this.composite = composite;

  }

  /**
   * takes in a layers object, and executes a specific operation upon it.
   *
   * @param layer represents an instance of ILayer.
   */
  @Override
  public void execute(ILayer layer) {
    layer.multiply(composite);

  }

}


