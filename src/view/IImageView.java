package view;

import java.io.IOException;


/**
 * Interface representing what the user sees. This interface represents the Text Based View
 * which can work with the text configuration with main. The program has two types of views;
 * a gui and text based.
 */

public interface IImageView {


  /**
   * Renders a given message to the data output in the implementation.
   *
   * @param message the message to be printed.
   * @throws IOException if the transmission of the message to the data output fails.
   */
  void renderMessage(String message) throws IOException;


}