package com.pvporbit.freetype;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;

public class FreeType {

	/* FreeType functions */

	public static native long FT_Init_FreeType();

	public static native boolean FT_Done_FreeType(long library); // TODO

	public static native long FT_New_Memory_Face(long library, ByteBuffer data, int length, int faceIndex);

	public static native boolean FT_Done_Face(long face);

	public static native boolean FT_Set_Pixel_Sizes(long face, float width, float height);

	public static native boolean FT_Load_Char(long face, char c, int flags);

	/* Java Object functions */

	public static Library newLibrary() {
		long library = FT_Init_FreeType();
		if (library != 0)
			return new Library(library);
		return null;
	}

	/* FreeType constants */

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
}