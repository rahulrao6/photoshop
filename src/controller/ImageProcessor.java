package controller;

import java.io.IOException;
import java.util.Scanner;

import controller.operations.BlueComponent;
import controller.operations.Difference;
import controller.operations.GreenComponent;
import controller.operations.Intensity;
import controller.operations.Luma;
import controller.operations.Multiply;
import controller.operations.NormalComponent;
import controller.operations.Operations;
import controller.operations.RedComponent;
import controller.operations.Screen;
import controller.operations.Value;
import model.ILayer;
import model.IProject;
import model.Image;
import model.Project;
import util.ImageUtil;
import view.IImageView;
import view.ImageView;

/**
 * An implementation of the processor that manages the connection between the model (the project)
 * and the view that displays and gets inputs from the user.
 */


public class ImageProcessor implements IProcessor {
  private IProject model;
  private Readable in;
  private IImageView view;


  /**
   * Constructor for the controller of the ImageProcessor.
   *
   * @param in    takes in a readable input.
   * @param view  takes in a view of type ImageView.
   * @param model takes in a model, and constructs the controller with these three.
   */
  public ImageProcessor(Readable in, ImageView view, Project model) {
    this.in = in;
    this.view = view;
    this.model = model;

  }


  @Override
  public void start() {
    Scanner sc = new Scanner(this.in);
    boolean quit = false;

    while (sc.hasNext() && !quit) {
      String in = sc.nextLine();
      String[] inputs = in.split(" ");
      switch (inputs[0]) {
        case "quit":
        case "q":
          quit = true;
          return;

        case "new-project":
          try {
            this.model = new Project(Integer.parseInt(inputs[1]),
                    Integer.parseInt(inputs[2]));
          } catch (NumberFormatException e) {
            throw new RuntimeException(e);
          }
          break;
        case "save-image":
          try {
            String s = this.model.overlay().ppmFormat(255);
            String ext = inputs[1].substring(inputs[1].lastIndexOf(".") + 1).
                    toLowerCase();
            Image image = model.overlay();
            if (ext.equals("ppm")) {
              ImageUtil.saveImage(s, inputs[1]);
            } else {
              ImageUtil.saveAllImage(image, inputs[1], ext);
            }
          } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
          }

          this.tryRenderMessage("Saved file.");

          break;
        case "add-image-to-layer":

          this.model.getLayer(inputs[1]).addImageToLayer(inputs[2],
                  Integer.parseInt(inputs[3]), Integer.parseInt(inputs[4]));

          this.tryRenderMessage("Added image.");

          break;
        case "add-layer":

          this.model.addLayer(inputs[1]);


          this.tryRenderMessage("Added layer.");

          break;

        case "set-filter":

          commandOperations(inputs);

          this.tryRenderMessage("Filter applied to layer.");
          break;
        default:

          this.tryRenderMessage("Incorrect inputs, try again.");

          break;
      }
    }
  }

  /**
   * Helper method to assist program in also doing all filters, so this method handles filters
   * in controller.
   *
   * @param inputs from the previous method, that actually runs the program.
   */
  protected void commandOperations(String[] inputs) {
    Operations cmd;
    String in = inputs[2];

    switch (in) {
      case ("red-component"):
        try {
          cmd = new RedComponent(inputs[1]);
          cmd.execute(this.model.getLayer(inputs[1]));
        } catch (IllegalArgumentException e) {

          this.tryRenderMessage(e.getMessage());

        }
        break;
      case ("blue-component"):
        try {
          cmd = new BlueComponent(inputs[1]);
          cmd.execute(this.model.getLayer(inputs[1]));
        } catch (IllegalArgumentException e) {
          this.tryRenderMessage(e.getMessage());
        }
        break;

      case ("green-component"):
        try {
          cmd = new GreenComponent(inputs[1]);
          cmd.execute(this.model.getLayer(inputs[1]));
        } catch (IllegalArgumentException e) {

          this.tryRenderMessage(e.getMessage());
        }
        break;
      case ("normal"):
        try {
          cmd = new NormalComponent(inputs[1]);
          cmd.execute(this.model.getLayer(inputs[1]));
        } catch (IllegalArgumentException e) {

          this.tryRenderMessage(e.getMessage());
        }
        break;
      case ("screen"):
        try {
          ILayer layer = model.getLayer(inputs[1]);
          Image image = model.overlay();
          cmd = new Screen(inputs[1], image);
          cmd.execute(layer);
        } catch (IllegalArgumentException e) {

          this.tryRenderMessage(e.getMessage());
        }
        break;
      case ("multiply"):
        try {
          ILayer layer = model.getLayer(inputs[1]);
          Image image = model.overlay();
          cmd = new Multiply(inputs[1], image);
          cmd.execute(layer);
        } catch (IllegalArgumentException e) {

          this.tryRenderMessage(e.getMessage());
        }
        break;
      case ("difference"):
        try {
          ILayer layer = model.getLayer(inputs[1]);
          Image image = model.overlay();
          cmd = new Difference(inputs[1], image);
          cmd.execute(layer);
        } catch (IllegalArgumentException e) {

          this.tryRenderMessage(e.getMessage());
        }
        break;
      case ("brighten"):
      case ("darken"):
        try {
          if (inputs[1].equals("value")) {
            cmd = new Value(inputs[2], inputs[1]);
            cmd.execute(model.getLayer(inputs[1]));
          } else if (inputs[1].equals("intensity")) {
            cmd = new Intensity(inputs[2], inputs[1]);
            cmd.execute(model.getLayer(inputs[1]));
          } else if (inputs[1].equals("luma")) {
            cmd = new Luma(inputs[2], inputs[1]);
            cmd.execute(model.getLayer(inputs[1]));
          }
        } catch (IllegalArgumentException e) {
          this.tryRenderMessage(e.getMessage());
        }
        break;

      default:
        this.tryRenderMessage("Incorrect Inputs, please try entering again.");
        break;
    }


  }


  /**
   * Uses a try-catch to attempt to render a message. Done as a convenience for the controller.
   *
   * @param message the message attempting to be shown.
   * @throws IllegalStateException If an IO error occurs.
   */

  private void tryRenderMessage(String message) throws IllegalStateException {

    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Unknown IO error" + e.getMessage());
    }
  }


}
