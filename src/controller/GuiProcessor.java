package controller;

import java.awt.image.BufferedImage;

import controller.operations.BlueComponent;
import controller.operations.Difference;
import controller.operations.GreenComponent;
import controller.operations.Multiply;
import controller.operations.Operations;
import controller.operations.RedComponent;
import controller.operations.Screen;
import model.ILayer;
import model.IProject;
import model.Image;
import model.Project;
import util.ImageUtil;
import view.GuiView;


/**
 * controller for the GUI. Implements Features interface, and handles all GUI operations.
 * Seperate from ImageProcessor which runs the text-based scripting for the program.
 */

public class GuiProcessor implements Features {
  private GuiView guiView;
  private IProject project;

  private String currentLayer;

  public GuiProcessor(IProject project) {
    this.project = project;
  }


  public void setGuiView(GuiView guiView) {
    this.guiView = guiView;
  }

  @Override
  public void newProject(int height, int width) {
    this.project = new Project(height, width);
    updateImage();
    updateLayerList();
  }


  @Override
  public void addLayer(String name) {
    project.addLayer(name);
    updateLayerList();
    updateImage();

  }

  /**
   * removes a layer that is specified from the project.
   *
   * @param name name of the layer that is to be removed.
   */
  public void removeLayer(String name) {
    project.removeLayer(name);
    updateLayerList();
    updateImage();
  }

  @Override
  public void saveImage(String input, String sourcePath) {

    //String ppm = project.overlay().ppmFormat(255);
    //ImageUtil.saveImage(ppm, sourcePath);
    String ext = sourcePath.substring(sourcePath.lastIndexOf(".") + 1).
            toLowerCase();
    System.out.println(ext);
    Image image = project.overlay();
    if (ext.equals("ppm")) {
      System.out.println("Saving image with extension: " + ext);

      ImageUtil.saveImage(input, sourcePath);
    } else {
      ImageUtil.saveAllImage(image, sourcePath, ext);
    }

  }

  @Override
  public void addImage(String layerName, String sourcePath, int x, int y) {
    this.project.getLayer(layerName).addImageToLayer(sourcePath, x, y);
    updateImage();
    updateLayerList();
  }

  @Override
  public void updateImage() {
    Image image = project.overlay();
    BufferedImage buff = image.toBufferedImage(image);
    guiView.setImage(buff);
    guiView.repaint();

  }

  @Override
  public void updateLayerList() {
    // Clear the layers list in the GUI view
    guiView.clearLayersList();

    // Add the layer names to the GUI view
    for (String layerName : project.getLayerNames()) {
      guiView.addLayer(layerName);
    }


  }

  @Override
  public void redComponent(String layerName) {
    ILayer layer = project.getLayer(layerName);
    Operations cmd = new RedComponent(layerName);
    cmd.execute(layer);
    updateImage();

  }

  @Override
  public void greenComponent(String layerName) {
    ILayer layer = project.getLayer(layerName);
    Operations cmd = new GreenComponent(layerName);
    cmd.execute(layer);
    updateImage();

  }

  @Override
  public void blueComponent(String layerName) {
    ILayer layer = project.getLayer(layerName);
    Operations cmd = new BlueComponent(layerName);
    cmd.execute(layer);
    updateImage();


  }

  @Override
  public void setCurrentLayer(String layerName) {
    this.currentLayer = layerName;
    updateImage();


  }

  @Override
  public void layerSelected(String layerName) {
    ILayer layer = project.getLayer(layerName);
    this.currentLayer = layerName;
    if (layer != null) {
      BufferedImage im = project.overlay().toBufferedImage(project.overlay());
      guiView.setImage(im);
    }

  }

  @Override
  public void difference(String layerName) {
    ILayer layer = project.getLayer(layerName);
    Image image = project.overlay();
    Operations cmd = new Difference(layerName, image);
    cmd.execute(layer);
    updateImage();

  }

  @Override
  public void multiply(String layerName) {
    ILayer layer = project.getLayer(layerName);
    Image image = project.overlay();
    Operations cmd = new Multiply(layerName, image);
    cmd.execute(layer);
    updateImage();

  }

  @Override
  public void screen(String layerName) {
    ILayer layer = project.getLayer(layerName);
    Image image = project.overlay();
    Operations cmd = new Screen(layerName, image);
    cmd.execute(layer);
    updateImage();

  }

  /**
   * exits the program completely.
   */
  @Override
  public void exit() {
    System.exit(0);

  }


}
