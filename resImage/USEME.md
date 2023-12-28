# USEME FILE 


## USEME FOR SCRIPTING:
In terms of the scripting file, there are multiple commands that are shown in the Scripting.txt
file in the ResImage folder. Each of the GUI buttons have a corresponding command to be used
for the text version or the file. You can look at the description related to the buttons for more
information about each function or feature. The Images in the folder + scripting are all related
to one another as well, so you can look at those to gather exactly how you want to create your 
collage. 

### Here are all the commands: 
### Adding a Project


new-project xHeight xWidth
### Adding a layer

add-layer layerName

### Adding a image

add-image-to-layer layerName sourcePath xCoord yCoord

### Filters:
set-filter layerName red-component

set-filter layerName blue-component

set-filter layerName green-component

set-filter layerName difference

set-filter layerName multiply

set-filter layerName screen


### Saving
save-image sourcePath




### This is the USEME file for the GUI.

In order to better understand this USEME and the components of the GUI
and the UI/buttons, there is a file called GUISS.png in the rest folder
with a screenshot of an image loaded into the GUI, and we will use that
picture of the GUI as reference for when i am indicating functionality.

The explanation behind the GUI, and the overall functionality
of the application, what it can do, and its design is in the README. IN the USEME
we will only be describing what each button and its corresponding functionality does.

## Buttons/Functionality of GUI:

### Main Project Functionality:

New Project: New project is a button, that when pressed, will
prompt you for the maximum height and width of the project,
then create a new project of those dimensions for you, and will also intiialize the model.

Add Layer: Add layer is a button, that when pressed, will create a new transparent layer
with nothing on it, with the name that you indiciate when prompted. This layer will
be added to the LayersList which appears (in the top right/middle right of the application).

Load Image:
To load an image, you can select the layer (on the LayersList - not actually necessary),
as I made this program actually ask the user what layer they want to apply on (so this allows more
flexibility to the user (and wont be wrong based on whatever is selected)), then you can click
the Load Image button which prompts you for the layerName (which already had to be created
), then the sourcePath of that image, and the x and y coordinate. It will then
place the image on the layer (and you can see that).

Save Image:
To save an image, make sure there is something (some kind of image) being displayed
on the GUI, then click the Save Image button, which prompts for a file Name,
which will be the new name of the image, and then a sourcepath,
this will correspondingly save the image to that location in ppm format.

Exit: Exit button exits the GUI completely.

### Filters:

Red Component:
Applies the red component filter to the selected layer. The selected layer is based on
the layer that is being clicked on the LayersList (mouse click), the selected
layer is indicated by a blue highlight through the name when selected.

Blue Component: Applies the Blue component filter the same way.

Green Component: Applies the Green component filter the same way.

#### Blending

These filters work slightly different from the previous filters, as they
dont just apply to a particular layer rather the entire composite image.
So these take in a layer when prompted (which is the current layer),
that you want the filter to be executed on, and then automatically
takes in the composite image and applies the filter accordingly.
Difference: Once images have been placed on layers in project, click the Difference
button and enter the layer you want the filter to apply to, and it will apply the filter
as specified.
Multiply: Applies the Multiply Blending Filter in the same manner by clicking the button and
entering the layerName that you want the filter to apply to.
Screen: Applies the Screen Filter in the same way, where it takes in a layerName
that is the actual layer being executed upon.

LayersList:
LayersList is a pane that is supposed to hold all the different layers
of the project and display them so the user can see what layers have been added,
and actually execute certain filters on certain layers respectively.
In the top right or middle right of the screen, It will display a list of layers,
and when you click on a layer, a blue highlight appears indiciating
that that is the current layer (which you can apply certain filters to).

SelectLayer: Click the layer in the LayersList to actually select that layer
for operational purposes. 