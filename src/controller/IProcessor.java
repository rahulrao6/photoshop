package controller;

/**
 * Represents the controller for the scripting implementation of our project. Uses text-based
 * and scripting application modes for running the program.
 */
public interface IProcessor {


  /**
   * runs the program by creating a new model (project) and a view. Then the controller waits
   * for input from the user and goes to the appropriate case.
   */
  public void start();

}
