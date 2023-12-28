package controller.operations;

import model.ILayer;

/**
 * Represents the normal component in the Operations interface.
 */
public class NormalComponent implements Operations {

  /**
   * constructor for the normal component.
   *
   * @param name of the layer.
   */
  public NormalComponent(String name) {
    String lay;
    lay = name;
  }

  /**
   * takes in a layers object, and executes a specific operation upon it.
   *
   * @param layer represents an instance of ILayer.
   */
  @Override
  public void execute(ILayer layer) {
    layer.normal();


  }
}
