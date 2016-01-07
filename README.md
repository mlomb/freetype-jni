# FreeType-jni
Java interface to the [FreeType](http://www.freetype.org/) library with [JNI](https://en.wikipedia.org/wiki/Java_Native_Interface).
# Usage
## Initialization
To create a new library use:
```Java
Library library = FreeType.newLibrary();
```
To create a face with a .ttf file:
```Java
Face face = library.newFace("res/OpenSans-Regular.ttf", 0);
```
If there is an error, `library` or `face` will be `null`.
## Retrieve data
TODO
## Clean up
To delete the objects please use the `dispose` method:
```Java
face.dispose();
library.dispose();
```
# License
Please read the [LICENSE](https://github.com/Dementor100/freetype-jni/blob/master/LICENSE) file (MIT).
