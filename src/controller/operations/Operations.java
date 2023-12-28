package controller.operations;

import model.ILayer;

/**
 * represents the operations that would be applied to layers.
 */
public interface Operations {


  /**
   * takes in a layers object, and executes a specific operation upon it.
   *
   * @param layer represents an instance of ILayer.
   */
  public void execute(ILayer layer);

}
