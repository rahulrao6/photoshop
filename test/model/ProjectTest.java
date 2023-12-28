package model;

import org.junit.Test;

import util.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * This class represents the projectTest class. It currently contains test that tests the entire
 * blending functionality on different layers, pixels, and projects, and also has a test where
 * it takes in a project, creates multiple layers, applies operations, saves, and checks certain
 * values to see if it applied filters properly.
 */
public class ProjectTest {
  ILayer layer1;

  IImage image2;

  ILayer layer1White;


  IImage image2White;

  ILayer layer1Black;


  ILayer layerWhite;
  IImage imageBlack;

  IImage image2Black;


  /**
   * sets up basic image and layer, randomly pixel.
   */
  private void setUp() {


    layer1 = new ImageLayer(new Image(new Pixel[][]{
            {new Pixel(0, 0, 0), new Pixel(255, 255, 255)},
            {new Pixel(100, 100, 100), new Pixel(200, 200, 200)}}
    ));

    image2 = new Image(new Pixel[][]{
            {new Pixel(255, 255, 255), new Pixel(0, 0, 0)},
            {new Pixel(200, 200, 200), new Pixel(100, 100, 100)}}
    );

  }

  /**
   * sets up white layer and image.
   */
  private void setUpWhite() {

    //represents a white layer
    layer1White = new ImageLayer(new Image(new Pixel[][]{
            {new Pixel(255, 255, 255), new Pixel(255, 255, 255)},
            {new Pixel(255, 255, 255), new Pixel(255, 255, 255)}}
    ));
    //represents the white composite image
    image2White = new Image(new Pixel[][]{
            {new Pixel(255, 255, 255), new Pixel(255, 255, 255)},
            {new Pixel(255, 255, 255), new Pixel(255, 255, 255)}}
    );

  }

  /**
   * sets up black layer and image.
   */
  private void setUpBlack() {
    Project project = new Project(100, 100);
    project.addLayer("layer1Black");
    //represents a black current layer
    layer1Black = new ImageLayer(new Image(new Pixel[][]{
            {new Pixel(0, 0, 0), new Pixel(0, 0, 0)},
            {new Pixel(0, 0, 0), new Pixel(0, 0, 0)}}
    ));
    //represents a black composite image
    image2Black = new Image(new Pixel[][]{
            {new Pixel(0, 0, 0), new Pixel(0, 0, 0)},
            {new Pixel(0, 0, 0), new Pixel(0, 0, 0)}}
    );

  }

  /**
   * sets up white layer and black composite image.
   */
  private void setUpBlackWhite() {
    layerWhite = new ImageLayer(new Image(new Pixel[][]{
            {new Pixel(255, 255, 255), new Pixel(255, 255, 255)},
            {new Pixel(255, 255, 255), new Pixel(255, 255, 255)}}
    ));
    imageBlack = new Image(new Pixel[][]{
            {new Pixel(0, 0, 0), new Pixel(0, 0, 0)},
            {new Pixel(0, 0, 0), new Pixel(0, 0, 0)}}
    );
  }

  @Test
  public void testDifference() {
    setUp();
    Image image = layer1.toIm();
    String expected = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 100 100 100 255 255 255 200 200 200 ";
    //check to see if image prints to ppm as expected and has proper values.
    assertEquals(expected, image.ppmFormat(255));
    layer1.difference(image2);
    Image image2 = layer1.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 100 100 100 255 255 255 100 100 100 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image2.ppmFormat(255));


  }

  @Test
  public void testDifferenceWhite() {
    setUpWhite();
    layer1White.difference(image2White);
    Image image2 = layer1White.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image2.ppmFormat(255));
  }

  @Test
  public void testDifferenceBlack() {
    setUpBlack();
    Project project = new Project(1000, 1000);
    project.addLayer("Layer1Black");
    layer1Black.difference(image2Black);
    Image image3 = layer1Black.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image3.ppmFormat(255));
  }

  @Test
  public void testDifferenceWhiteBlack() {
    setUpBlackWhite();
    layerWhite.difference(imageBlack);
    Image image4 = layerWhite.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 255 255 255 255 255 255 255 255 255 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image4.ppmFormat(255));
  }

  @Test
  public void testMultipleDifferenceWhiteBlack() {
    setUpBlackWhite();
    layerWhite.difference(imageBlack);
    layerWhite.difference(imageBlack);
    layerWhite.difference(imageBlack);

    Image image4 = layerWhite.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 255 255 255 255 255 255 255 255 255 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image4.ppmFormat(255));
  }


  // testing multiply

  @Test
  public void testMultiply() {
    setUp();
    Image image = layer1.toIm();
    String expected = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 100 100 100 255 255 255 200 200 200 ";
    //check to see if image prints to ppm as expected and has proper values.
    assertEquals(expected, image.ppmFormat(255));
    layer1.multiply(image2);
    Image image2 = layer1.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 78 78 78 0 0 0 78 78 78 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image2.ppmFormat(255));


  }

  @Test
  public void testMultiplyWhite() {
    setUpWhite();
    layer1White.multiply(image2White);
    Image image2 = layer1White.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 255 255 255 255 255 255 255 255 255 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image2.ppmFormat(255));
  }

  @Test
  public void testMultiplyBlack() {
    setUpBlack();
    Project project = new Project(1000, 1000);
    project.addLayer("Layer1Black");
    layer1Black.multiply(image2Black);
    Image image3 = layer1Black.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image3.ppmFormat(255));
  }

  @Test
  public void testMultiplyWhiteBlack() {
    setUpBlackWhite();
    layerWhite.multiply(imageBlack);
    Image image4 = layerWhite.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image4.ppmFormat(255));
  }

  @Test
  public void testMultipleMultiplyWhiteBlack() {
    setUpBlackWhite();
    layerWhite.multiply(imageBlack);
    layerWhite.multiply(imageBlack);
    layerWhite.multiply(imageBlack);

    Image image4 = layerWhite.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image4.ppmFormat(255));
  }

  // testing screen

  @Test
  public void testScreen() {
    setUp();
    Image image = layer1.toIm();
    String expected = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 100 100 100 255 255 255 200 200 200 ";
    //check to see if image prints to ppm as expected and has proper values.
    assertEquals(expected, image.ppmFormat(255));
    layer1.screen(image2);
    Image image2 = layer1.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 221 221 221 255 255 255 221 221 221 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image2.ppmFormat(255));


  }

  @Test
  public void testScreenWhite() {
    setUpWhite();
    layer1White.screen(image2White);
    Image image2 = layer1White.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 255 255 255 255 255 255 255 255 255 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image2.ppmFormat(255));
  }

  @Test
  public void testScreenBlack() {
    setUpBlack();
    Project project = new Project(1000, 1000);
    project.addLayer("Layer1Black");
    layer1Black.screen(image2Black);
    Image image3 = layer1Black.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image3.ppmFormat(255));
  }

  @Test
  public void testScreenWhiteBlack() {
    setUpBlackWhite();
    layerWhite.screen(imageBlack);
    Image image4 = layerWhite.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 255 255 255 255 255 255 255 255 255 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image4.ppmFormat(255));
  }

  @Test
  public void testMultipleScreenWhiteBlack() {
    setUpBlackWhite();
    layerWhite.screen(imageBlack);
    layerWhite.screen(imageBlack);
    layerWhite.screen(imageBlack);

    Image image4 = layerWhite.toIm();
    String expected2 = "P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 255 255 255 255 255 255 255 255 255 ";
    //check to see if pixel values are changed properly after difference method is called
    //values are flipped properly
    assertEquals(expected2, image4.ppmFormat(255));
  }

  // tests all the new blending filters on top of each other.
  @Test
  public void testMixed() {
    setUp();
    layer1.difference(image2);
    Image image = layer1.toIm();
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 100 100 100 255 255 255 100 100 100 ", image.ppmFormat(255));
    layer1.screen(image2);
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 221 221 221 255 255 255 160 160 160 ", image.ppmFormat(255));
    layer1.multiply(image2);
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 173 173 173 0 0 0 62 62 62 ", image.ppmFormat(255));


  }

  // tests all the new blending filters on top of each other twice.
  @Test
  public void testMixed2() {
    setUp();
    layer1.difference(image2);
    Image image = layer1.toIm();
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 100 100 100 255 255 255 100 100 100 ", image.ppmFormat(255));
    layer1.screen(image2);
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 221 221 221 255 255 255 160 160 160 ", image.ppmFormat(255));
    layer1.multiply(image2);
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 173 173 173 0 0 0 62 62 62 ", image.ppmFormat(255));
    layer1.difference(image2);
    image = layer1.toIm();
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "0 0 0 27 27 27 0 0 0 38 38 38 ", image.ppmFormat(255));
    layer1.screen(image2);
    image = layer1.toIm();
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 205 205 205 0 0 0 123 123 123 ", image.ppmFormat(255));
    layer1.multiply(image2);
    image = layer1.toIm();
    assertEquals("P3\n" +
            "2 2\n" +
            "255\n" +
            "255 255 255 160 160 160 0 0 0 48 48 48 ", image.ppmFormat(255));
  }

  /**
   * This test runs an entire project, and creates three different layers. It applies the redComp
   * difference, screen, and multiply to the specified layer and tests individual coordinates
   * within the new created images (using pixel value from gimp or previous implementation) in order
   * to check and see if the actual pixel value is as it should be in the new image.
   */
  @Test
  public void testProject2() {
    try {
      Project project = new Project(1000, 1000);
      project.addLayer("Hi");
      project.getLayer("Hi").addImageToLayer(
              "Image Editor Program/images/background.ppm", 0, 0);

      project.addLayer("Ho");
      project.getLayer("Ho").addImageToLayer(
              "Image Editor Program/images/background.ppm", 500, 0);

      project.addLayer("He");
      project.getLayer("He").addImageToLayer(
              "Image Editor Program/images/background.ppm", 0, 500);
      //applies the redComponent filter to the layer Ho
      project.getLayer("Ho").redComponent();
      project.getLayer("He").difference(project.overlay());

      //check to see if save is working
      // - checked and works
      ImageUtil.saveImage("meOw", "Image Editor Program/images/meOw.ppm");


      Image image = project.getLayer("He").toIm();

      // check pixel values at three distinct locations (as to not check entire
      // pixel image and loop)

      assertEquals("66 255 255", image.getPixelAtCoord(0, 0).toString());
      assertEquals("62 255 255", image.getPixelAtCoord(100, 0).toString());
      assertEquals("59 255 255", image.getPixelAtCoord(100, 100).toString());

      //apply the other filters to the project and see what the culmination creates
      project.getLayer("He").screen(project.overlay());
      image = project.getLayer("He").toIm();
      assertEquals("195 195 195", image.getPixelAtCoord(0, 0).toString());
      assertEquals("195 195 195", image.getPixelAtCoord(100, 0).toString());
      assertEquals("194 194 194", image.getPixelAtCoord(100, 100).toString());

      project.getLayer("He").multiply(project.overlay());
      image = project.getLayer("He").toIm();
      assertEquals("72 72 72", image.getPixelAtCoord(0, 0).toString());
      assertEquals("73 73 73", image.getPixelAtCoord(100, 0).toString());
      assertEquals("74 74 74", image.getPixelAtCoord(100, 100).toString());


    } catch (Exception e) {
      throw new IllegalArgumentException("Not proper arguments");
    }

  }

  @Test
  public void testProjectJPG() {
    try {
      Project project = new Project(1000, 1000);
      project.addLayer("Hi");
      project.getLayer("Hi").addImageToLayer(
              "Image Editor Program/res/backJPG.jpg", 0, 0);

      project.addLayer("Ho");
      project.getLayer("Ho").addImageToLayer(
              "Image Editor Program/res/backJPG.jpg", 500, 0);

      project.addLayer("He");
      project.getLayer("He").addImageToLayer(
              "Image Editor Program/res/backJPG.jpg", 0, 500);
      //applies the redComponent filter to the layer Ho
      project.getLayer("Ho").redComponent();
      project.getLayer("He").difference(project.overlay());

      //check to see if save is working
      // - checked and works
      ImageUtil.saveImage("meOw", "Image Editor Program/images/meOOw.jpg");


      Image image = project.getLayer("He").toIm();

      // check pixel values at three distinct locations (as to not check entire
      // pixel image and loop)

      assertEquals("28 224 222", image.getPixelAtCoord(0, 0).toString());
      assertEquals("28 224 222", image.getPixelAtCoord(100, 0).toString());
      assertEquals("28 224 222", image.getPixelAtCoord(100, 100).toString());

      //apply the other filters to the project and see what the culmination creates
      project.getLayer("He").screen(project.overlay());
      image = project.getLayer("He").toIm();
      assertEquals("141 240 239", image.getPixelAtCoord(0, 0).toString());
      assertEquals("141 240 239", image.getPixelAtCoord(100, 0).toString());
      assertEquals("141 240 239", image.getPixelAtCoord(100, 100).toString());

      project.getLayer("He").multiply(project.overlay());
      image = project.getLayer("He").toIm();
      assertEquals("22 170 168", image.getPixelAtCoord(0, 0).toString());
      assertEquals("22 170 168", image.getPixelAtCoord(100, 0).toString());
      assertEquals("22 170 168", image.getPixelAtCoord(100, 100).toString());


    } catch (Exception e) {
      throw new IllegalArgumentException("Not proper arguments");
    }

  }
}

  /*

  @Test
  public void testProject() {

    Project project = new Project(1000, 1000);
    Readable readable = new StringReader(" ");
    GuiView view = new GuiView();

    GuiProcessor processor = new GuiProcessor(project);
    view.addFeatures(processor);
    processor.newProject(1000, 1000);
    processor.addLayer("Layer1");
    processor.setCurrentLayer("Layer1");
    processor.addImage("Layer1", "Image Editor Program/images/testing.ppm",
            0, 0);
    processor.addLayer("Layer2");
    processor.addImage("Layer2", "Image Editor Program/images/tako.ppm",
            0, 0);
    processor.difference("Layer1");
    processor.saveImage("DifferenceImage",
            "Image Editor Program/images/differenceImage.ppm");


    assertEquals("", view.toString());


  }


  @Test
  public void testChecks() {
    Project project = new Project(1000, 1000);
    GuiProcessor processor = new GuiProcessor(project);
    GuiView view = new GuiView();
    processor.setGuiView(view);
    view.addFeatures(processor);
    //create a new project
    processor.newProject(1000, 1000);
    try {
      processor.addLayer("Layer1");
    } catch (Exception e) {
      throw new IllegalArgumentException("Layer is not being added properly");
    }
    // processor.setCurrentLayer("Layer1");
    try {
      processor.addImage("Layer1", "Image Editor Program/images/testing.ppm",
              0, 0);
    } catch (Exception e) {
      throw new IllegalArgumentException("Layer is not being added properly");
    }

    processor.addLayer("Layer2");
    //processor.setCurrentLayer("Layer2");
    // processor.addImage("Layer2","Image Editor Program/images/tako.ppm",
    //        0,0);
    try {
      processor.difference("Layer1");
      assertEquals("", project.getLayer("Layer1").toString());
    }


    catch (Exception e) {
      throw new IllegalArgumentException("Issue with difference");
    }
  }
   */


