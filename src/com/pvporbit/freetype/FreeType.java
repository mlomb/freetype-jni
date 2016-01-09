package com.pvporbit.freetype;

import java.nio.ByteBuffer;

public class FreeType {

	/* FreeType functions */

	// -
	public static native long    FT_Init_FreeType();

	// Library
	public static native boolean FT_Done_FreeType(long library); 
	public static native int[]   FT_Library_Version(long library); // [major, minor, patch]
//	public static native long    FT_Open_Face(long library, FT_Open_Args args, long faceIndex); // Nope.
//	public static native long    FT_New_Face(long library, String filepathname, long faceIndex); // Please use 'FT_New_Memory_Face' or preferable 'library.newFace(path)'
	public static native long    FT_New_Memory_Face(long library, ByteBuffer data, int length, long faceIndex);
	
	// Face
	public static native short   FT_Face_Get_ascender(long face);
	public static native short   FT_Face_Get_descender(long face);
	public static native long    FT_Face_Get_face_flags(long face);
	public static native long    FT_Face_Get_face_index(long face);
	public static native String  FT_Face_Get_family_name(long face);
	public static native short   FT_Face_Get_heigth(long face);
	public static native short   FT_Face_Get_max_advance_height(long face);
	public static native short   FT_Face_Get_max_advance_width(long face);
	public static native long    FT_Face_Get_num_faces(long face);
	public static native long    FT_Face_Get_num_glyphs(long face);
	public static native long    FT_Face_Get_style_flags(long face);
	public static native String  FT_Face_Get_style_name(long face);
	public static native short   FT_Face_Get_underline_position(long face);
	public static native short   FT_Face_Get_underline_thickness(long face);
	public static native short   FT_Face_Get_units_per_EM(long face);
	public static native long[]  FT_Face_Get_Kerning(long face, char left, char right, int mode); // [x, y]
	public static native long    FT_Face_Get_KerningX(long face, char left, char right, int mode); // Horizontal
	public static native long    FT_Face_Get_KerningY(long face, char left, char right, int mode); // Vertical
	public static native long    FT_Face_Get_glyph(long face); /* Pointer to FT_GlyphSlot */
	public static native long    FT_Face_Get_size(long face); /* Pointer to FT_Size */
	
	public static native boolean FT_Done_Face                (long face);
	public static native boolean FT_Reference_Face           (long face);
	public static native boolean FT_HAS_KERNING              (long face); // Yup, the real name is capitalized
	public static native boolean FT_Face_CheckTrueTypePatents(long face);
	public static native boolean FT_Face_SetUnpatentedHinting(long face, boolean value);
	public static native int     FT_Get_Char_Index           (long face, int code);
	public static native boolean FT_Select_Size              (long face, int strikeIndex);
	public static native boolean FT_Load_Char                (long face, char c, int flags);
//	public static native boolean FT_Attach_File              (long face, String filepathname); // Nope.
//	public static native boolean FT_Attach_Stream            (long face, FT_Open_Args parameters); // Nope.
	public static native boolean FT_Request_Size             (long face, SizeRequest sizeRequest);
	public static native boolean FT_Set_Pixel_Sizes          (long face, float width, float height);
	public static native boolean FT_Load_Glyph               (long face, int glyphIndex, int loadFlags);
	public static native boolean FT_Set_Char_Size            (long face, int char_width, int char_height, int horz_resolution, int vert_resolution);

	// Size
	public static native long    FT_Size_Get_metrics(long size); /* Pointer to SizeMetrics */
	
	// Size Metrics
	public static native long    FT_Size_Metrics_Get_ascender   (long sizeMetrics);
	public static native long    FT_Size_Metrics_Get_descender  (long sizeMetrics);
	public static native long    FT_Size_Metrics_Get_height     (long sizeMetrics);
	public static native long    FT_Size_Metrics_Get_max_advance(long sizeMetrics);
	public static native long    FT_Size_Metrics_Get_x_ppem     (long sizeMetrics);
	public static native long    FT_Size_Metrics_Get_x_scale    (long sizeMetrics);
	public static native long    FT_Size_Metrics_Get_y_ppem     (long sizeMetrics);
	public static native long    FT_Size_Metrics_Get_y_scale    (long sizeMetrics);
	
	// GlyphSlot
	public static native long    FT_GlyphSlot_Get_linearHoriAdvance(long glyphSlot);
	public static native long    FT_GlyphSlot_Get_linearVertAdvance(long glyphSlot);
	public static native long[]  FT_GlyphSlot_Get_advance          (long glyphSlot);
	public static native long    FT_GlyphSlot_Get_advanceX         (long glyphSlot);
	public static native long    FT_GlyphSlot_Get_advanceY         (long glyphSlot);
	public static native int     FT_GlyphSlot_Get_format           (long glyphSlot);
	public static native int     FT_GlyphSlot_Get_bitmap_left      (long glyphSlot);
	public static native int     FT_GlyphSlot_Get_bitmap_top       (long glyphSlot);
	public static native long    FT_GlyphSlot_Get_bitmap           (long glyphSlot); /* Pointer to Bitmap */
	public static native long    FT_GlyphSlot_Get_metrics          (long glyphSlot); /* Pointer to GlyphMetrics */

//	public static native long    FT_Get_Glyph                      (long glyphSlot); /* Pointer to Glyph */ TODO
	public static native boolean FT_Render_Glyph                   (long glyphSlot, int renderMode);
	
	// GlyphMetrics
	public static native long FT_Glyph_Metrics_Get_width       (long glyphMetrics);
	public static native long FT_Glyph_Metrics_Get_height      (long glyphMetrics);
	public static native long FT_Glyph_Metrics_Get_horiAdvance (long glyphMetrics);
	public static native long FT_Glyph_Metrics_Get_vertAdvance (long glyphMetrics);
	public static native long FT_Glyph_Metrics_Get_horiBearingX(long glyphMetrics);
	public static native long FT_Glyph_Metrics_Get_horiBearingY(long glyphMetrics);
	public static native long FT_Glyph_Metrics_Get_vertBearingX(long glyphMetrics);
	public static native long FT_Glyph_Metrics_Get_vertBearingY(long glyphMetrics);
	
	// Bitmap
	public static native int        FT_Bitmap_Get_width       (long bitmap);
	public static native int        FT_Bitmap_Get_rows        (long bitmap);
	public static native int        FT_Bitmap_Get_pitch       (long bitmap);
	public static native short      FT_Bitmap_Get_num_grays	  (long bitmap);
	public static native char       FT_Bitmap_Get_palette_mode(long bitmap);
	public static native char       FT_Bitmap_Get_pixel_mode  (long bitmap);
	public static native ByteBuffer FT_Bitmap_Get_buffer	  (long bitmap);
	
	// Glyph
	// Implemented ↑ | TODO ↓
	
	
	/* Java Object functions */

	public static Library newLibrary() {
		long library = FT_Init_FreeType();
		if (library <= 0)
			return null;
		return new Library(library);
	}

	/* FreeType constants */

	public static enum FT_Render_Mode {
		FT_RENDER_MODE_NORMAL,
		FT_RENDER_MODE_LIGHT,
		FT_RENDER_MODE_MONO,
		FT_RENDER_MODE_LCD,
		FT_RENDER_MODE_LCD_V,

		FT_RENDER_MODE_MAX
	}
	
	public static enum FT_Size_Request_Type {
	    FT_SIZE_REQUEST_TYPE_NOMINAL,
	    FT_SIZE_REQUEST_TYPE_REAL_DIM,
	    FT_SIZE_REQUEST_TYPE_BBOX,
	    FT_SIZE_REQUEST_TYPE_CELL,
	    FT_SIZE_REQUEST_TYPE_SCALES,

	    FT_SIZE_REQUEST_TYPE_MAX
	};

	// http://www.freetype.org/freetype2/docs/reference/ft2-base_interface.html#FT_LOAD_XXX
	public static final int FT_LOAD_DEFAULT = 0x0;
	public static final int FT_LOAD_NO_SCALE = (1 << 0);
	public static final int FT_LOAD_NO_HINTING = (1 << 1);
	public static final int FT_LOAD_RENDER = (1 << 2);
	public static final int FT_LOAD_NO_BITMAP = (1 << 3);
	public static final int FT_LOAD_VERTICAL_LAYOUT = (1 << 4);
	public static final int FT_LOAD_FORCE_AUTOHINT = (1 << 5);
	public static final int FT_LOAD_CROP_BITMAP = (1 << 6);
	public static final int FT_LOAD_PEDANTIC = (1 << 7);
	public static final int FT_LOAD_IGNORE_GLOBAL_ADVANCE_WIDTH = (1 << 9);
	public static final int FT_LOAD_NO_RECURSE = (1 << 10);
	public static final int FT_LOAD_IGNORE_TRANSFORM = (1 << 11);
	public static final int FT_LOAD_MONOCHROME = (1 << 12);
	public static final int FT_LOAD_LINEAR_DESIGN = (1 << 13);
	public static final int FT_LOAD_NO_AUTOHINT = (1 << 15);
	/* Bits 16..19 are used by `FT_LOAD_TARGET_' */
	public static final int FT_LOAD_COLOR = (1 << 20);
	public static final int FT_LOAD_COMPUTE_METRICS = (1 << 21);

	static { // Horrible method to load the library, but w/e.
		try {
			if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
				int bits = 86;
				if (System.getProperty("os.arch").contains("64"))
					bits = 64;
				System.loadLibrary("freetype26MT_x" + bits);
			} else
				throw new Exception("Operating system not supported.");
		} catch (UnsatisfiedLinkError e) {
			System.err.println("Can't find the native file for FreeType-jni.");
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}