package controller;

/**
 * add new feature (method) for each function of our program. Represents all the functionality
 * that this application can do in the GUI.
 */
public interface Features {

  /**
   * creates a new project of specified dimensions.
   */
  void newProject(int height, int width);

  /**
   * Adds a layer to the project.
   *
   * @param name name of the Layer to be added.
   */
  void addLayer(String name);

  /**
   * saves the Image to a certain path to the file explorer.
   *
   * @param input      Name of the Image to be saved.
   * @param sourcePath of the image to be saved, including the .file (ppm) at the end of path.
   */
  void saveImage(String input, String sourcePath);

  /**
   * adds an Image to a particular layer.
   *
   * @param layerName  name of the Layer that the image should be added to.
   * @param sourcePath of the image that needs to be loaded in.
   * @param x          coordinate, where the image is going to be placed.
   * @param y          coordinate, where the image is going to be placed.
   */
  void addImage(String layerName, String sourcePath, int x, int y);

  /**
   * removes a layer from the project.
   *
   * @param layerName name of the layer that is to be removed.
   */
  void removeLayer(String layerName);

  /**
   * updates the Image, and makes sure it shows the present view.
   */
  void updateImage();

  /**
   * updates the layers list, function that is called after performing functions on list.
   */
  void updateLayerList();

  /**
   * performs the red component function on the layer.
   *
   * @param layerName name of the layer, function is to be applied to.
   */
  void redComponent(String layerName);

  /**
   * performs the green component function on the layer.
   *
   * @param layerName name of the layer, function is to be applied to.
   */
  void greenComponent(String layerName);

  /**
   * performs the blue component function on the layer.
   *
   * @param layerName name of the layer, function is to be applied to.
   */
  void blueComponent(String layerName);

  /**
   * sets the current layer of the project.
   *
   * @param layerName the name of the layer that is to be selected.
   */
  void setCurrentLayer(String layerName);

  /**
   * sets image to current view for the layer.
   *
   * @param layerName name of the Layer being selected.
   */
  void layerSelected(String layerName);

  /**
   * applies the difference function to the selected layer.
   *
   * @param layerName is the name of the current layer.
   */
  void difference(String layerName);

  /**
   * applies the multiply function on the two layers.
   *
   * @param layerName the current layer's name.
   */
  void multiply(String layerName);

  /**
   * applies the screen function on the two layers.
   *
   * @param layerName the current layer's name.
   */
  void screen(String layerName);

  /**
   * exits the program completely.
   */
  void exit();

}
