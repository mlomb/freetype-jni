# FreeType-jni
Java binding to the <a href="http://www.freetype.org/" target="_blank">FreeType</a> library using <a href="https://en.wikipedia.org/wiki/Java_Native_Interface" target="_blank">JNI</a>.

You can use the native functions directly to FreeType or use the defined classes in Java style.
# Contents
* [Download](#Download)
* <a href="http://www.freetype.org/freetype2/docs/reference/ft2-toc.html" target="_blank">FreeType documentation</a>
* [Library](#Library)
  * [LibraryVersion](#LibraryVersion)
  * [Face](#Face)
    * GlyphSlot
      * Bitmap
      * Advance
      * GlyphMetrics
    * Kerning
    * Size
      * SizeMetrics
* [FreeType constants](#FreeTypeConstants)
* [Native functions](#NativeFunctions)
* Examples
  * Integration with LWJGL
* [License](#License) (MIT)

<a name="Download"></a>
## Download
You can download the latest release in the `releases` folder.<br>
You will find the library JAR wich you must to use in your project with a name like `freetypeXX-jni_x.x`. Where `XX` is the version of FreeType and `x.x` the version of FreeType-jni.<br>
This library requires natives which can be found in the folder `natives` inside the corresponding os folder.<br>
Also, you will find the source code JAR if you want to include it into your project.
<a name="Library"></a>
## Library
To initialize the FreeType library, create a new `Library` object using the FreeType class. Each `library` is completely independent from the others; it is the ‘root’ of a set of objects like fonts, faces, sizes, etc.
<a name="Library-newLibrary"></a>
```Java
Library library = FreeType.newLibrary();
```
If something goes wrong, `library` will be `null`.
Some objects like `Library` or `Face` must to be deleted when finished work to avoid memory lakes.
```Java
library.delete();
```
#### Methods in Library
<a name="Library-delete"></a>
* `boolean delete()`: Destroy the library object and all of it's childrens, including faces, sizes, etc. `false` means success.
<a name="Library-getVersion"></a>
* `LibraryVersion getVersion()`: Returns a [LibraryVersion](#LibraryVersion) object containing the information about the version of FreeType.
<a name="Library-newFace"></a>
* `Face newFace(String file, int faceIndex)`: Create a new [Face](#Face) object from file. It will return `null` in case of error.
* `Face newFace(byte[] file, int faceIndex)`: Create a new [Face](#Face) object from a byte[]. It will return `null` in case of error.
* `Face newFace(ByteBuffer file, int faceIndex)`: Create a new [Face](#Face) object from a ByteBuffer. It will return `null` in case of error. **Take care that the ByteByffer must be a direct buffer created with [Utils](#Utils).[newBuffer](#Utils-newBuffer) and filled with [Utils](#Utils).[fillBuffer](#Utils-fillBuffer).**

<a name="LibraryVersion"></a>
## LibraryVersion
This is a simple class wich contains the version information about FreeType.
You can instantiate this class with [library](#Library).[getVersion](#Library-getVersion) and then retrive the information with:
* `int getMajor()`
* `int getMinor()`
* `int getPatch()`

And if you like you can use it as String:
```Java
System.out.println("FreeType " + libVersion + " initialized.");
```
To get something like `FreeType 2.6.0 initialized.`.
<a name="Face"></a>
## Face
A handle to a given typographic face object. A face object models a given typeface, in a given style.<br>
Use [library](#Library).[newFace](#Library-newFace) to create a new face object from a file o data source.<br>
Each `Face` object also owns a single [GlyphSlot](#GlyphSlot) object, and every time you call [loadGlyph](#Face-loadGlyph) or [loadChar](#Face-loadChar) the slot's contents is ereased with the new data.<br>
You must to destroy it aswell as the Library.
#### Methods in Face
TODO MORE DOCS
<a name="FreeTypeConstants"></a>
## FreeType constants
You can access to the FreeType constants with the `FreeTypeConstants` class.
<a name="NativeFunctions"></a>
## Native functions
Here are listed with the real FreeType name functions to call them directly.<br>
If you want to retrieve a public field you must to look for `FT_Object_Name_Get_field_name`. e.g. for the object `FT_Glyph_Metrics` the field `vertBearingY` is `FT_Glyph_Metrics_Get_vertBearingY`. All the documentation for the other functions can be found in the <a href="http://www.freetype.org/freetype2/docs/reference/ft2-toc.html" target="_blank">FreeType documentation</a>.

Here is the list:
* `long FT_Init_FreeType()`
* `boolean FT_Done_FreeType(long library)`
* `LibraryVersion FT_Library_Version(long library)`
* `long FT_New_Memory_Face(long library, ByteBuffer data, int length, long faceIndex)`
* `int FT_Face_Get_ascender(long face)`
* `int FT_Face_Get_descender(long face)`
* `long FT_Face_Get_face_flags(long face)`
* `int FT_Face_Get_face_index(long face)`
* `String FT_Face_Get_family_name(long face)`
* `int FT_Face_Get_heigth(long face)`
* `int FT_Face_Get_max_advance_height(long face)`
* `int FT_Face_Get_max_advance_width(long face)`
* `int FT_Face_Get_num_faces(long face)`
* `int FT_Face_Get_num_glyphs(long face)`
* `long FT_Face_Get_style_flags(long face)`
* `String FT_Face_Get_style_name(long face)`
* `int FT_Face_Get_underline_position(long face)`
* `int FT_Face_Get_underline_thickness(long face)`
* `int FT_Face_Get_units_per_EM(long face)`
* `long FT_Face_Get_glyph(long face)` **Pointer to FT_GlyphSlot**
* `long FT_Face_Get_size(long face)` **Pointer to FT_Size**
* `long FT_Get_Track_Kerning(long face, long point_size, int degree)`
* `Kerning FT_Face_Get_Kerning(long face, char left, char right, int mode)`
* `boolean FT_Done_Face(long face)`
* `boolean FT_Reference_Face(long face)`
* `boolean FT_HAS_KERNING(long face)`
* `String FT_Get_Postscript_Name(long face)`
* `boolean FT_Select_Charmap(long face, int encoding)`
* `boolean FT_Set_Charmap(long face, long charmap)`
* `boolean FT_Face_CheckTrueTypePatents(long face)`
* `boolean FT_Face_SetUnpatentedHinting(long face, boolean value)`
* `int[] FT_Get_First_Char(long face)` **[charcode, glyphIndex]**
* `int FT_Get_Next_Char(long face, long charcode)`
* `int FT_Get_Char_Index(long face, int code)`
* `int FT_Get_Name_Index(long face, String name)`
* `String FT_Get_Glyph_Name(long face, int glyphIndex)`
* `short FT_Get_FSType_Flags(long face)`
* `boolean FT_Select_Size(long face, int strikeIndex)`
* `boolean FT_Load_Char(long face, char c, int flags)`
* `boolean FT_Request_Size(long face, SizeRequest sizeRequest)`
* `boolean FT_Set_Pixel_Sizes(long face, float width, float height)`
* `boolean FT_Load_Glyph(long face, int glyphIndex, int loadFlags)`
* `boolean FT_Set_Char_Size(long face, int char_width, int char_height, int horz_resolution, int vert_resolution)`
* `long FT_Size_Get_metrics(long size)` **Pointer to FT_Size_Metrics**
* `int FT_Size_Metrics_Get_ascender(long sizeMetrics)`
* `int FT_Size_Metrics_Get_descender(long sizeMetrics)`
* `int FT_Size_Metrics_Get_height(long sizeMetrics)`
* `int FT_Size_Metrics_Get_max_advance(long sizeMetrics)`
* `int FT_Size_Metrics_Get_x_ppem(long sizeMetrics)`
* `int FT_Size_Metrics_Get_x_scale(long sizeMetrics)`
* `int FT_Size_Metrics_Get_y_ppem(long sizeMetrics)`
* `int FT_Size_Metrics_Get_y_scale(long sizeMetrics)`
* `long FT_GlyphSlot_Get_linearHoriAdvance(long glyphSlot)`
* `long FT_GlyphSlot_Get_linearVertAdvance(long glyphSlot)`
* `Advance FT_GlyphSlot_Get_advance(long glyphSlot)`
* `int FT_GlyphSlot_Get_format(long glyphSlot)`
* `int FT_GlyphSlot_Get_bitmap_left(long glyphSlot)`
* `int FT_GlyphSlot_Get_bitmap_top(long glyphSlot)`
* `long FT_GlyphSlot_Get_bitmap(long glyphSlot)` **Pointer to FT_Bitmap**
* `long FT_GlyphSlot_Get_metrics(long glyphSlot)` **Pointer to FT_Glyph_Metrics**
* `boolean FT_Render_Glyph(long glyphSlot, int renderMode)`
* `int FT_Glyph_Metrics_Get_width(long glyphMetrics)`
* `int FT_Glyph_Metrics_Get_height(long glyphMetrics)`
* `int FT_Glyph_Metrics_Get_horiAdvance(long glyphMetrics)`
* `int FT_Glyph_Metrics_Get_vertAdvance(long glyphMetrics)`
* `int FT_Glyph_Metrics_Get_horiBearingX(long glyphMetrics)`
* `int FT_Glyph_Metrics_Get_horiBearingY(long glyphMetrics)`
* `int FT_Glyph_Metrics_Get_vertBearingX(long glyphMetrics)`
* `int FT_Glyph_Metrics_Get_vertBearingY(long glyphMetrics)`
* `int FT_Bitmap_Get_width(long bitmap)`
* `int FT_Bitmap_Get_rows(long bitmap)`
* `int FT_Bitmap_Get_pitch(long bitmap)`
* `short FT_Bitmap_Get_num_grays(long bitmap)`
* `char FT_Bitmap_Get_palette_mode(long bitmap)`
* `char FT_Bitmap_Get_pixel_mode(long bitmap)`
* `ByteBuffer FT_Bitmap_Get_buffer(long bitmap)`
* `int FT_Get_Charmap_Index(long charmap)`

<a name="License"></a>
# License
Please read the <a href="https://github.com/Dementor100/freetype-jni/blob/master/LICENSE" target="_blank">LICENSE</a> file (MIT).