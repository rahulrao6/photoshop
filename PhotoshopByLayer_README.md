# Photoshop By Layer Application - Technical
## Overview
The Photoshop By Layer Application (PBLA) is a sophisticated image manipulation and collage creation tool that adheres to the principles of Object-Oriented Design (OOD). It provides a model-view-controller (MVC) architecture, allowing for separation of concerns, modular design, and the ability to extend functionality without modifying existing code.

## System Architecture and Design
#### Model
The model is the backbone of PBLA, consisting of classes that represent image data and operations:

Project: Manages layers in a LinkedHashMap, maintaining insertion order for correct layer composition and efficient access.

ImageLayer: Represents a layer, holding a 2D array of Pixel objects. It supports image manipulation operations such as adding images to layers, applying color filters, and blend modes.

Pixel: Encapsulates RGBA values, serving as the building block of an image. It offers color adjustment methods and forms the basis for image processing algorithms.

HSL: Handles Hue, Saturation, and Lightness values for color operations that are more naturally expressed outside the RGB model.

#### Utility
ImageUtil: Provides methods for reading and writing image files in both PPM format and common image formats (JPEG, PNG) using ImageIO.
#### View
GuiView: Implements the user interface with Swing components, displaying the image and layer information, and capturing user interactions.
### Design Patterns
Command Pattern: User actions trigger commands that can be easily modified or extended, showcasing polymorphism and encapsulation.
Factory Pattern: Pixel creation from HSL values abstracts the instantiation logic, reducing dependencies on concrete classes.

### Data Structures
LinkedHashMap: Used for ordered and efficient layer management. It is essential for the proper application of operations that depend on the layer order.
Algorithms
### Image Processing
Color Filtering: Includes operations for isolating color channels (red, green, blue) and conversion to grayscale using luminance-based calculations.

Blend Modes: Implements modes such as difference, multiply, and screen, manipulating pixel data based on their color values and the values of underlying pixels.

HSL Conversion: Converts between RGB and HSL color models, allowing operations like brightness adjustments based on lightness.

### Image Transformation
Layer Composition: Layers are composed to form a final image. Transparency is considered to allow underlying layers to contribute to the result.

Brightness and Luminance: Adjusts pixel brightness or luminance, with the option to use the value, intensity, or luma as the basis for the adjustment.

##### Performance Considerations
The application is designed for efficiency with large images. It optimizes image operations to reduce computation, particularly when iterating over pixel matrices.

#### GUI and Swing
The GUI, constructed using Java Swing, provides a responsive and intuitive interface. It includes components such as buttons, lists, and scroll panes to interact with the image layers. Error handling is managed through dialog boxes, and the user experience is considered for usability.

#### Future Enhancements
Parallel Processing: Implementing multithreading to expedite image operations.

Layer Previews: Miniature previews in the GUI for quick layer visualization.

Undo/Redo Functionality: A history stack to enable reverting and reapplying changes.

##### Technical Highlights
Image Representation: Images are internally represented using a 2D array of Pixel objects. This choice facilitates direct manipulation of pixel data and simplifies the implementation of image processing algorithms.

Transparency Handling: Alpha values are fully supported, enabling complex layering effects and ensuring compatibility with common image formats.

Color Models: Besides RGB, the application supports HSL, offering more natural operations for certain color transformations.

Scripting Capability: The application can execute scripted commands for batch processing, demonstrating the flexibility of the command pattern.

##### Conclusion
PBLA is an example of a robust, well-architected software system. It combines object-oriented design, design patterns, and data structures to create a comprehensive tool for image editing and manipulation. The documentation presented here provides a deep dive into the system's architecture, algorithms, and potential for future development.