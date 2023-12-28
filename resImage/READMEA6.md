# Assignment 6 README (NEW):

What files do you need to send along for only the views
to compile?
This list must include:
the interfaces and implementations for the view itself.
For each file, briefly explain why that file is necessary
for the view to work
In order for the view to function, at the beginning of the project.
I had to provide the features interface for the controller, the IProject
interface for the model

Features Interface - This file is necessary for the view to work,
as this is passed into the view. (this was discussed as the one allowance) to break
the mvc pattern, and is necessary for the gui and its button to function.
IProject interface - This file is necessary as it is the model interface for this project,
and is necessary for the view in the text-based program. But, an issue
was found due to this interface.
ImageLayer class - This class is required due to the IProject interface
needing to return an ImageLayer to get the current Layer, this
was a method built for the GUI (so the current layer can be targeted), and
this exposes the ImageLayer class. I was not able to fix this decoupling issue
and would recommend creating a factory pattern for imageLayers
and moving functionality, so that the project can constructor layers
to be shown, selected, and returned without needing the entire class.

Design:
In this assignment, I changed some design in relation to interfaces and classes,
and tried to use more flexible design throughout (using interface types instead of class).
and using more encapsulated variables throughout. This was how i was able
to decouple a majority of the model for the GUIVIEW. In my old model,
all model representations (Pixel, Image, Ilayer), was needed
for the old view to work because that was not updated with the new design
with using more general types, so after these changes I was able to decouple
most of my overall design. I believe I am generally adhering
to the MVC design, and have kept controller operations, model, and the view seperate.
The two issues with my deisgn, would be the ImageLayer class being exposed
due to a single getter method in my project interface using a specific type,
as well as the Features interface being passed into the view. This extension.
was very easy to add this time, and just requires adding additional
methods or classes to the program. This design extends the design and improves
from A5. Please look at the rest of the README for a more descriptive
description of the program.

Changes:
I added all functionality to the scripting file, it can now support
JPEG and PNG functionality as well. The GUI can support that functionality as well
, it is important to take into mind that the size of the image matters.

# Assignment 5 README:

## Application Summary:

This is an Image Processing Program, that consists of many working parts. The ways
to use this program is to input "file" and the sourcepath into the Main argument, input "text"
as the argument, to open up the interactive text-based program, that runs through
the terminal, or the default: which is to open the GUI and run the program
in a graphical user interface, which is the most complete and worked out solution
of the three ways to run this program (as of now). The class Diagram is also in the res folder.

## Functionality:

The program and its corresponding GUI have a multitude of seperate functions,
it can create a new project of certain height and width, add multiple
layers with different names (that hold the value of layer), see
the composite image pop up (as images are loaded into layers). You can
add images to these layers, and apply mutliple filters upon the entire image and
even particular layers themself (depending on the filter).
Each particular functionality (button) will be shown and explained in the USEME
file, to better help a user operate this program. Right now,
the filters that are working (as buttons on GUI) are screen, multiply, red component,
blue component, and green component (they work slightly differently).
Value, Luma, and Intensity, only currently work with text-based
or script program modes.

### How to use program (Jar File vs Command Line):

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

### Design Heirarchy + Explanation:

The design of this project consists of multiple components.
At the base of the model, we have the IPixel interface, and
an equivalent to that base, the IHSL interface. From there,
this builds upward. A singular Image is represented by the IImage
interface/Image class, and that is: the final compiled image,
the composite image that is being worked on, and what is loaded
into/saved in the Image processing program. From that, we have the ILayer
which represents a single layer on a project, images can be added to layers
at certain coordinates. As the final, main, model, we have the IProject
interface and Project class which represent ALL the layers
and the entire project as a whole. Filters, in this program,
are considered to be operations, and have command classes
in the controller which execute upon the ILayer (as filters are aplied to layers).
This allows for flexibility in adding future functionality,
as we can create new classes in the controller that execute
on new functionality in the model. The actual logic to iterate
each pixel on the layer, is found in ILayer and its corresponding implementation.
To modify each individual pixel, we also have a method in the IPixel
interface to apply the function to each pixel, and replicate
for the entire layer. We have kept Saving and Reading in this project
in the Util class, as to keep that functionality away from the model.

As per text-based scripting, we have kept the previous controller implementation:
which consists of the IProcessor and ImageProcessor, which works with
file or text inputs in the main (how to use specified later in ReadME).

For that application-use, we also have the IImageView and ImageView classes
that represent the text-based view, but we have not altered that functionality
for this assignment (the text-based does not work for new components).

As per the GUI:
We have seperated the GUI into two components, the GUI controller
which is the Features Interface (that details all functionality of the GUI),
and its corresponding GuiProcessor implementation. The controller, here,
has both the model and GUI view so it can use both to perform operations
on each.

The GUI View, consists of everything that is seen in the GUI.
There are panes and buttons that are set, and then following,
The Features Interface (controller) is passed, and each button
(and corresponding functionality) has an actionListener
added to it (so it actually does something) and these take in
inner classes (that actually do the functionality and implement ActionListener).
For some of the past features (Red, Blue, Green), I did not create
an inner class (as no new inputs were required) and used the lambda
function to call the filters on each of the selected layers.
Although this slightly breaks MVC (controller is passed to view),
this seems to be the best way to decouple everything and remove dependencies
and should be the only place where this program breaks it, due to the GUI
functionality.

### Design Changes from A4:

From A4, we made a few key design changes. (I specified most of the design heirarchy for this
assignment above), and have included the ReadMe for A4 as well below.
We tried to keep the majority of the design the same, and create future
functionality based on our existing design, but improve it, so
there were some changes to be made. We kept the same design in that,
we continued to have the same model (LinkedHashMap), which represents a list of
layers, BUT in our previous design, layers used Pixels (because we ran out of time to
implement Image), so we changed the ImageLayer to take in an Image instead.
We also completely moved saving out of the model to the Util. The filters
still have command classes that execute the function upon the layer,
but the actual functionality still happens in the layer (did not have time to change), and
i understand the pros and cons of that in terms of mutation. We also changed some code in the Pixel
as it was not representing the RGB properly.
On top of that, It was mainly actually implementing the code (since we didnt finish functionality),
last time, and our design was relatively okay towards the end.

## A5 New:

These will describe the additional classes or implementations
that have been added to the A5. The A4 Representations carry over as we did
not change much from the existing classes from last time
(other than complete functionality).
as we did not change much of existing models

#### Model:

IHSL & HSL: Interface and implementation representing the Hue,
Saturation, and Light representation of Pixels and RGB. These
can be converted to and from RGB, and work with the Pixel representation
through different methods. Within the pixel class, the conversions
are applied and finish (to and from HSL) for any filters or operations
when it is called upon at each Pixel level.

IPixel & Pixel: Added additional functionality (same as last),
but added conversion methods to and from HSL (as stated above) + additional filter applications.

IImage & Image: now contain functionality to convert to ppm, and
also convert to a buffered Image, for the view.

#### View:

The GUIView: is the new class that has been added to the view. This class
extends a JFrame and represents all the visual UI/UX elements
of the program in the graphical user interface created by java swing.
This view uses a Features from the controller in order to get functionality
for the buttons.

#### Controller:

Operations:
Within the operations package in the controller which consists
of the command design elements, we added Difference, Multiply, and Screen.

Difference: represents an instance of Difference, which applies the difference
algorithim of absolute value of difference of two pixels and executes that
upon the layer.

Multiply: represents an instance of Multiply, which applies the multiply
algorithim of absolute value of Multiply of two pixels and executes that
upon the layer.

Screen: represents an instance of Screen, which applies the screen
algorithim of absolute value of screen of two pixels and executes that
upon the layer.

Features Interface: This represents all the functionality that our
application can do in the GUI, and this is passed to the view.

GUIProcessor: implements Features, and actually executes the different
'features' or functionality of the program by calling those functions
on the model and view accordingly.

Util: Added both read and save functionality, cleaned it up.

##### Incomplete Parts:

In the GUI portion of this application, most features have been implemented.
The difference filter produces sometimes an opaque image when blending
two others, but I am not sure why, but for smaller pixels/layers and tests work.
I was not able to finish debugging this or figuring out if it really is a problem.

#### Images:

For this program, I used the sample image tako.ppm, and then
the other two images I used back.ppm, and background.ppm, where white
and red images that I created using GIMP for the purpose of this program
and the rights to that are mine, since I created it.

## Assignment 4 README:

Design:
We have seperated our design into multiple different components:
Model:
The model includes the ILayer, IProject, IImage, and IPixel.

ILayer - represents an individual layer, out of a collection of layers (project).
Performs operations on the images by applying filters to each layer.
ImageLayer - implementation of ILayer - is represented by a 2d array of Pixels (was supposed to be
an image but we ran out of time and could not change the pixel to image before submitting).

IProject - represents the model.
Project - an instance of Iproject. Is represented by LinkedHashmap,
please ignore confusing comments, ran out of time to edit and clean up code.
This class can add layers and instantiate the model.

IImage- represents an Image (or 2d array of Pixels_
Image - a wrapper class for Pixels so it is easier to manage and understand. can also create
pixel and image specific functions.
IPixel - represents a single pixel with a RGBA value.
Pixel - an instance of IPixel, represents each pixel in an image and its RGB value.

The Controller:
Operations - Represents all the filters that needed to be added on specfically to a layer.
It passes the layer as an argument and executes each filter (as defined in the ImageLayer)
on each layer through the command design patter.

Classes:
BlueComponent - an operation that apply the blue component filter upon an image.
GreenCOmponent - an operation that apply the green component filter upon an image.
Intensity - an operation that apply the intensity component filter upon an image.
Luma - an operation that apply the luma component filter upon an image.
RedComponent - - an operation that apply the red component filter upon an image.
Save - was supposed to represent the saving of a function, but did not implement.
Value - an operation that applys the value filter on an image.
IProcessor - represents the controller for the program which reads and writes the information
around the application.
ImageProcessor- the controller that actually takes in the model and view and runs the program. This
is
where the user enters inputs and theyre passed to the model.

The view:
IImageView - represents the view interface of the Image Processing Application.
ImageView - represents the view for the image processing application.

ImageUtil - Has the loading image, reading ppm functionality. This holds some of the functionality
to handle images.

Functionality: Unfortunately, due to our time constraints, we were unable to get our program
to be fully functional. The loading and saving of images was not working, and as such the filters
were in a similar predicament. We understand where our design was flawed and how to improve it and
the functionality of that and are working towards that for the next assignment.

Images:
We did not include any sample images, as our program could not handle loading and saving of images
so we could not show the filtered images.

Scripting:
Our scripting file works, as should be typed after the program is ran.
our scripts our below (and also in another file named Scripting.txt)
but in this file, there are descriptions of each of the commands for
the application.

//creates a project of size 800 600
new-project 800 600

//adds a layer with name tako-blue
add-layer tako-blue

//sets filter as particular component
set-filter tako-blue blue-component

//sets image to particular coordinate on layer
add-image-to-layer tako-blue image/tako.ppm 100 50

// Sets the filter to darken according to the luma value
set-filter eevee-dark darken-luma

// Saves the final rendered image to the given path
save-image images/tako-eevee.ppm




