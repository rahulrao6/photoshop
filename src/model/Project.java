package model;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * An implementation of IProject that stores all the layers in a linked hashMap, so they can be
 * referred to in a specific order.
 */

public class Project implements IProject {


  private final Map<String, ImageLayer> project;
  private final int pHeight;
  private final int pWidth;

  /**
   * Constructor for a project that just takes in a height and width
   * and creates an empty LinkedHashMap for the layers.
   *
   * @param height how many pixels high the project is.
   * @param width  how many pixels long the project is.
   */

  public Project(int height, int width) {
    if (height < 0 || width < 0) {
      throw new IllegalArgumentException("Negative Canvas Size.");
    }

    this.pHeight = height;
    this.pWidth = width;
    // using LinkedHashMap to have order + key/value.
    this.project = new LinkedHashMap<>();

  }


  @Override
  public void addLayer(String name) {

    Pixel[][] pix = new Pixel[this.pHeight][this.pWidth];


    for (int i = 0; i < this.pHeight; i++) {
      for (int j = 0; j < this.pWidth; j++) {
        pix[i][j] = new Pixel(255, 255, 255, 0);

      }
    }
    Image imag = new Image(pix);


    ImageLayer layer = new ImageLayer(imag);


    this.project.put(name, layer);

  }

  @Override
  public int getProjectHeight() {

    return this.pHeight;
  }

  @Override
  public int getProjectWidth() {

    return this.pWidth;
  }


  //@Override
  //public ImageLayer getLayer(String name) {
  //   return this.project.get(name);
  //}

  @Override
  public Image getLayerImage(String name) {
    ImageLayer layer = this.project.get(name);
    Image image = layer.toIm();
    return image;
  }


  @Override
  public Image overlay() {


    Pixel[][] pixel = new Pixel[this.pHeight][this.pWidth];


    Pixel transparent = new Pixel(255, 255, 255, 0);
    for (int i = 0; i < this.pHeight; i++) {
      for (int j = 0; j < this.pWidth; j++) {
        pixel[i][j] = transparent;
      }
    }

    for (ImageLayer layer : this.project.values()) {
      Pixel[][] layerPixel = layer.getLayers();
      for (int i = 0; i < this.pHeight; i++) {
        for (int j = 0; j < this.pWidth; j++) {
          Pixel layerP = layerPixel[i][j];
          if (layerP.getAlpha() != 0) {
            pixel[i][j] = layerP;

          }

        }
      }
    }

    return new Image(pixel);
  }

  @Override
  public void removeLayer(String layerName) {
    if (this.project.containsKey(layerName)) {
      this.project.remove(layerName);
    } else {
      throw new IllegalArgumentException("Layer with the specified name does not exist.");
    }
  }

  @Override
  public void clearLayerList() {

    this.project.clear();
  }

  @Override
  public List<String> getLayerNames() {

    return new ArrayList<>(this.project.keySet());
  }

  @Override
  public ImageLayer getLayer(String name) {
    return this.project.get(name);

  }


}

