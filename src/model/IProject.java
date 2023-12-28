package model;

import java.util.List;


/**
 * A project is the over arching object holding everything together. It is a map of all the layers
 * in the current project.
 */
public interface IProject {


  /**
   * adds a layer to the project (hashmap of layers).
   *
   * @param name of new layer and also key value.
   */

  void addLayer(String name);


  /**
   * Returns the height of the layer.
   *
   * @return the height as an integer.
   */

  int getProjectHeight();

  /**
   * Returns the width of the layer.
   *
   * @return the width as an integer.
   */

  int getProjectWidth();

  /**
   * gets the layer, based on the name of the layer.
   *
   * @param name of the layer.
   * @return an ImageLayer value based on key from name.
   */
  ImageLayer getLayer(String name);

  /**
   * gets the Image from a layer given a name.
   *
   * @param name of the layer to be converted into an image.
   * @return an Image representation of a layer.
   */
  Image getLayerImage(String name);


  /**
   * Creates the final image from all the filters.
   *
   * @return the image with all the filters applied.
   */

  Image overlay();


  /**
   * Removes a layer from a project.
   *
   * @param layerName the name of the layer to be removed.
   * @throws IllegalArgumentException If the layer cannot be found with the given name.
   */

  void removeLayer(String layerName) throws IllegalArgumentException;


  /**
   * Clears the map of all its values.
   */

  void clearLayerList();

  /**
   * Gives a list of all the layers.
   *
   * @return a list of all the current layers as strings.
   */

  List<String> getLayerNames();


}
