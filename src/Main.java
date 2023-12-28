import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import controller.GuiProcessor;
import controller.ImageProcessor;
import model.Project;
import view.GuiView;
import view.ImageView;

/**
 * This class represents a final class: Main, which takes in command line arguments in order to run
 * the program. This main accepts file, text, or default inputs.
 */
public final class Main {

  /**
   * Main program for the Image Processing program.
   *
   * @param args command line argument, to choose how to run the application.
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      if (args[0].equalsIgnoreCase("file")) {
        Project project = new Project(1000, 1000);
        String filePath = args[1];
        Readable readable;
        try {
          readable = new FileReader(filePath);
        } catch (FileNotFoundException e) {
          throw new RuntimeException(e);
        }
        Appendable a = new StringBuilder();
        ImageView view = new ImageView(project, a);
        ImageProcessor processor = new ImageProcessor(readable, view, project);
        processor.start();
      } else if (args[0].equalsIgnoreCase("text")) {
        Project project = new Project(1000, 1000);
        ImageView view = new ImageView(project, System.out);
        ImageProcessor processor = new ImageProcessor(new InputStreamReader(System.in),
                view, project);
        processor.start();
      } else {
        Project project = new Project(1000, 1000);
        GuiProcessor processor = new GuiProcessor(project);
        GuiView view = new GuiView();
        processor.setGuiView(view);
        view.addFeatures(processor);
      }

    }
  }
}




  /*

   public static void main(String[] args) {
    Project project = new Project(1000, 1000);
    GuiProcessor processor = new GuiProcessor(project);
    GuiView view = new GuiView();
    processor.setGuiView(view);
    view.addFeatures(processor);

  }

  public static void main2(String[] args) {
//    Appendable a = new StringBuilder("");
//    Readable in = System.in;
    Project project = new Project(1000, 1000);
    ImageView view = new ImageView(project, System.out);
    ImageProcessor processor = new ImageProcessor(new InputStreamReader(System.in), view, project);
    processor.start();

  }


   */


