# Image Processing Program README (User Guide)

All files, including the USEME file, are located in the ResImage folder.

## Overall Description

The Image Processing Program is a powerful application
that allows users to manipulate and edit images with various filters and effects.
The program can be operated in three different ways: a scripting file,
an interactive text-based program, or a graphical user interface (GUI),
which offers the most comprehensive functionality.

## Features

Currently, the following features are available for users:

Create new projects with specified dimensions
Add multiple layers with different names
See the composite image as images are loaded into layers
Apply various filters to the entire image or specific layers
The available filters depend on the UI type. In the GUI,
users can access the screen, multiply, red component, blue component, and
green component filters. The value, luma, and intensity filters are
only available in the text-based or script program modes.



## Requirements and Dependencies

To run or compile the code, users need:

Java 11 or higher JRE
JUnit 4 for running tests

## Using the Program

Please refer to the USEME file located in the ResImage folder for detailed instructions
on how to use the program.

## To operate with main:

There are a few diffferent ways to use the program as explained earlier:
I. (JAR & SCRIPTING FILE IN RES FOLDER)

1. JAR FIle EXECUTABLE:
    2. in the res folder of the program, there is a jar file, and when clicked upon, it
       will open the GUI.
3. COMMAND LINE ARGUMENTS:
    4. the program main works with three command line arguments
        5. "file" - second input is the sourcepath, and you can run a scripting file
           with all commands through here and it will pass into the controller.
        6. "text" - opens the program in the terminal, in an interactiv text manner. From here
           you can type the same commands that are in the scripting file, in real time
           to execute the program.
        7. default: this is the default case, and if there are ANY other
           command line arguments in the main (other than nothing), it will use this case
           which will default and open up the GUI.

## Design

Our project is structured into three main components: Model, View, and Controller.
We have separated our design into these components to follow the MVC design pattern,
making it easier to manage and extend our software.

### Model

The model includes interfaces and classes representing different elements of an image processing
application, such as layers, projects, images, and pixels.

ILayer represents an individual layer in a collection of layers (project). ImageLayer, its
implementation, is represented by a 2D array of Pixels.
IProject represents the model as a whole. Project, an instance of IProject, is represented by a
LinkedHashMap.
IImage represents an Image, which is a 2D array of Pixels. Image is a wrapper class for Pixels,
making it easier to manage and understand, with the ability to create pixel and image-specific
functions.
IPixel represents a single pixel with an RGBA value. Pixel, an instance of IPixel, represents each
pixel in an image and its RGB value.

### Controller

The Controller package includes operations representing filters that can be applied to a layer. It
uses the command design pattern to apply filters, as defined in the ImageLayer, to each layer.

Classes:

BlueComponent, GreenComponent, RedComponent - operations that apply the corresponding color
component filter upon an image.
Intensity, Luma, Value - operations that apply the respective filters upon an image.
IProcessor represents the controller for the program, which reads and writes information around the
application. ImageProcessor is the controller that takes in the model and view and runs the program.
This is where the user enters inputs, and they're passed to the model.

### View

The View package consists of interfaces and classes representing the view of the Image Processing
Application.

IImageView represents the view interface of the application. ImageView represents the view for the
image processing application.
ImageUtil includes functionality for loading images and reading PPM files. This utility class
handles some of the functionality related to images.


## Image
The image was created by Gimp and had a previous creative content license which was okay to be used
as long as it was toward educational purposes. I also created the image (recreated) myself so
the example Image used is entirely owned by me. 




