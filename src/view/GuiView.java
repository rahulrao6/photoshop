package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Features;


/**
 * An implementation of JFrame for us to create a GUI version of our program. This class
 * also uses the Features Interface in order to decouple controller and view interactions.
 */

public class GuiView extends JFrame {
  private JButton saveImage;


  private JButton addImage;
  private JButton newProject;
  private JButton addLayer;
  private JButton redComponent;
  private JButton blueComponent;
  private JButton greenComponent;

  private JButton difference;

  private JButton multiply;

  private JButton screen;
  private JButton exitButton;


  private JLabel imageLabel;
  private JList<String> layersList;
  private DefaultListModel<String> layersListModel;


  /**
   * constructor for the GUIView that sets up the GUI.
   */
  public GuiView() {
    super();

    this.setSize(1000, 1000);
    this.setTitle("Image Processing Program");
    this.setLocation(500, 800);

    //this.setResizable(false);
    this.setMinimumSize(new Dimension(300, 400));
    this.getContentPane().setBackground(new Color(99, 137, 160, 255));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    this.setSize(1000, 1000);
    this.setTitle("Image Processing Program");
    this.setLocation(500, 800);

    this.setMinimumSize(new Dimension(300, 400));
    this.getContentPane().setBackground(new Color(99, 137, 160, 255));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    JScrollPane imageScrollPane;


    this.saveImage = new JButton("Save Image");
    this.add(this.saveImage);
    this.saveImage.setActionCommand("save-image");

    this.addImage = new JButton("Load Image");
    this.add(this.addImage);
    this.addImage.setActionCommand("load-image");

    this.newProject = new JButton("New Project");
    this.add(this.newProject);
    this.newProject.setActionCommand("new-project");

    this.addLayer = new JButton("Add Layer");
    this.add(this.addLayer);
    this.addLayer.setActionCommand("add-layer");

    this.redComponent = new JButton("Red Component");
    this.add(this.redComponent);
    this.redComponent.setActionCommand("red-component");

    this.greenComponent = new JButton("Green Component");
    this.add(this.greenComponent);
    this.greenComponent.setActionCommand("green-component");

    this.blueComponent = new JButton("Blue Component");
    this.add(this.blueComponent);
    this.blueComponent.setActionCommand("blue-component");

    this.difference = new JButton("Difference");
    this.add(this.difference);
    this.difference.setActionCommand("difference");

    this.multiply = new JButton("Multiply");
    this.add(this.multiply);
    this.multiply.setActionCommand("Multiply");

    this.screen = new JButton("Screen");
    this.add(this.screen);
    this.screen.setActionCommand("Screen");

    this.exitButton = new JButton("Exit");
    this.add(this.exitButton);
    this.exitButton.setActionCommand("exit");

    // Initialize image label and scroll pane

    this.imageLabel = new JLabel();
    imageScrollPane = new JScrollPane(imageLabel);
    this.add(imageScrollPane, BorderLayout.CENTER);

    // Initialize layers list
    layersListModel = new DefaultListModel<>();
    layersList = new JList<>(layersListModel);
    layersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    layersList.setVisibleRowCount(5);
    this.add(new JScrollPane(layersList), BorderLayout.WEST);

    this.pack();
    this.setVisible(true);


    this.pack();
    this.setVisible(true);
  }


  /**
   * takes in the parameter from the controller which and adds actionListeners to corresponding
   * buttons.
   *
   * @param features from the controller, with all operations of the application.
   */

  public void addFeatures(Features features) {

    this.addLayer.addActionListener(new AddLayerAL(features));
    this.newProject.addActionListener(new AddProjectAL(features));
    this.saveImage.addActionListener(new SaveImageAL(features));
    this.addImage.addActionListener(new AddImageAL(features));
    this.difference.addActionListener(new AddDifferenceAL(features));
    this.multiply.addActionListener(new AddMultiplyAL(features));
    this.screen.addActionListener(new AddScreenAL(features));
    this.layersList.addListSelectionListener(new LayersListSelectionHandler(features));

    this.redComponent.addActionListener(e -> features.redComponent(getSelectedLayer()));
    this.blueComponent.addActionListener(e -> features.blueComponent(getSelectedLayer()));
    this.greenComponent.addActionListener(e -> features.greenComponent(getSelectedLayer()));
    this.exitButton.addActionListener(e -> features.exit());


  }

  /**
   * Inner class that holds the addLayer functionality within the view.
   */
  private class AddLayerAL implements ActionListener {
    private Features features;

    public AddLayerAL(Features features) {

      this.features = features;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String layerName = JOptionPane.showInputDialog("Enter layer name:");
      if (!Objects.isNull(layerName)) {
        features.addLayer(layerName);
        refresh();

      }
    }
  }


  /**
   * Inner class that holds the Save image functionality within the view.
   * Asks user for inputs to save file.
   */
  private class SaveImageAL implements ActionListener {
    private Features features;

    public SaveImageAL(Features features) {
      this.features = features;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      JPanel panel = new JPanel(new GridLayout(0, 1));
      JTextField inputField = new JTextField(10);
      JTextField sourcePathField = new JTextField(10);

      panel.add(new JLabel("File name:"));
      panel.add(inputField);
      panel.add(new JLabel("Source path:"));
      panel.add(sourcePathField);

      int result = JOptionPane.showConfirmDialog(null, panel,
              "Enter File Name and Source Path",
              JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

      if (result == JOptionPane.OK_OPTION) {
        String input = inputField.getText();
        String sourcePath = sourcePathField.getText();
        features.saveImage(input, sourcePath);
        refresh();
      }
    }
  }

  /**
   * Inner class that holds the add image functionality within the view. Asks
   * users for inputs to add image to particular layer.
   */

  private class AddImageAL implements ActionListener {
    private Features features;

    public AddImageAL(Features features) {
      this.features = features;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      JPanel panel = new JPanel(new GridLayout(0, 1));
      JTextField layerNameField = new JTextField(10);
      JTextField sourcePathField = new JTextField(10);
      JTextField xField = new JTextField(5);
      JTextField yField = new JTextField(5);

      panel.add(new JLabel("Layer name:"));
      panel.add(layerNameField);
      panel.add(new JLabel("Source path:"));
      panel.add(sourcePathField);
      panel.add(new JLabel("X coordinate:"));
      panel.add(xField);
      panel.add(new JLabel("Y coordinate:"));
      panel.add(yField);

      int result = JOptionPane.showConfirmDialog(null, panel,
              "Enter Layer Name, Source Path, X, and Y",
              JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

      if (result == JOptionPane.OK_OPTION) {
        try {
          String layerName = layerNameField.getText();
          String sourcePath = sourcePathField.getText();
          int x = Integer.parseInt(xField.getText());
          int y = Integer.parseInt(yField.getText());
          features.addImage(layerName, sourcePath, x, y);
          refresh();
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null,
                  "Invalid input. Please enter valid numbers for X and Y.",
                  "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  /**
   * Inner class that holds the creating a project functionality within the view.
   * Creates a project baseed on the given height and width by the user.
   */

  private class AddProjectAL implements ActionListener {
    private Features features;

    public AddProjectAL(Features features) {
      this.features = features;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String heightString = JOptionPane.showInputDialog("Enter project height:");
      String widthString = JOptionPane.showInputDialog("Enter project width:");

      if (heightString != null && widthString != null) {
        try {
          int height = Integer.parseInt(heightString);
          int width = Integer.parseInt(widthString);
          features.newProject(height, width);
          refresh();
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Invalid input. " +
                          "Please enter valid numbers for height and width.", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  /**
   * Inner class that holds applying difference function functionality within the view.
   * Asks the users for which layers this will be applied upon.
   */
  private class AddDifferenceAL implements ActionListener {
    private Features features;

    public AddDifferenceAL(Features features) {
      this.features = features;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String layerName = JOptionPane.showInputDialog("Enter layer Name:");

      if (layerName != null) {
        try {
          features.difference(layerName);
          refresh();

        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Invalid input. " +
                          "Please enter valid inputs", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  /**
   * Inner class that holds applying multiply function functionality within the view.
   * Asks the users for which layers this will be applied upon.
   */
  private class AddMultiplyAL implements ActionListener {
    private Features features;

    public AddMultiplyAL(Features features) {
      this.features = features;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String layerName = JOptionPane.showInputDialog("Enter layer Name:");

      if (layerName != null) {
        try {
          features.multiply(layerName);
          refresh();

        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Invalid input. " +
                          "Please enter valid inputs", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }

  /**
   * Inner class that holds applying screen function functionality within the view.
   * Asks the users for which layers this will be applied upon.
   */
  private class AddScreenAL implements ActionListener {
    private Features features;

    public AddScreenAL(Features features) {
      this.features = features;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String layerName = JOptionPane.showInputDialog("Enter layer Name:");

      if (layerName != null) {
        try {
          features.screen(layerName);
          refresh();

        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(null, "Invalid input. " +
                          "Please enter valid inputs", "Error",
                  JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }


  /**
   * holds the functionality for the list of layers, and also makes sure that the selected layer
   * that the user is selecting through mouse input will be the current layer constantly and
   * proceed to refresh after.
   */

  class LayersListSelectionHandler implements ListSelectionListener {
    private Features features;

    public LayersListSelectionHandler(Features features) {
      this.features = features;
    }

    /**
     * sets the current layer based on what the user is selecting.
     *
     * @param e the event that characterizes the change.
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (!e.getValueIsAdjusting()) {
        String selectLayer = layersList.getSelectedValue();
        if (selectLayer != null) {
          features.setCurrentLayer(selectLayer);
        }
        resetFocus();
        refresh();
      }
    }
  }


  /**
   * Sets the label to be the given image.
   *
   * @param image the image that will show up on the label.
   */

  public void setImage(Image image) {
    ImageIcon icon = new ImageIcon(image);
    this.imageLabel.setIcon(icon);
    refresh();
  }

  /**
   * adds a layer to the LayerList in the GUI.
   *
   * @param layerName name of layer to be added.
   */

  public void addLayer(String layerName) {
    this.layersListModel.addElement(layerName);
    refresh();
  }

  /**
   * removes the layer based on a given name from the GUI list.
   *
   * @param layerName name of the layer to be removed.
   */

  public void removeLayer(String layerName) {

    this.layersListModel.removeElement(layerName);
    refresh();
  }

  /**
   * gets the selected layer from the layerList.
   *
   * @return the selected Value.
   */

  public String getSelectedLayer() {

    return this.layersList.getSelectedValue();
  }

  /**
   * clears the layerList model completely.
   */

  public void clearLayersList() {

    this.layersListModel.clear();
  }


  /**
   * Refreshes the GUI to display whatever new information that has been done as well
   * as validating all the components already present.
   */

  public void refresh() {
    this.revalidate();
    this.repaint();
  }


  /**
   * Resets the focus to a component, allowing it to take input.
   */

  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

}






