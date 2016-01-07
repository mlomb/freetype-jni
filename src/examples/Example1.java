package examples;

import com.pvporbit.freetype.FreeType;

public class Example1 {

	public static void main(String args[]) throws Exception {
		String libName = "freetype26MT_x64";
		String fontPath = "res/OpenSans-Regular.ttf";

		/* --- Load library from selected native folder --- */
		// Native folder ${workspace_loc:FreeType-jni/freetype/builds/x64}
		System.loadLibrary(libName);
		System.out.println("Library loaded.");

		/* --- Get library pointer --- */
		long library = FreeType.FT_Init_FreeType();
		if (library <= 0)
			throw new Exception("Error initializing FreeType.");
		System.out.println("FreeType initialized, pointer=" + library);

		/* --- Create face from .TTF --- */
		long face = FreeType.newFace(library, fontPath, 0); // We can use FT_New_Memory_Face but it requires a ByteBuffer so here is a help function to load it from a file easily.
		if (face <= 0)
			throw new Exception("Error creating face from file '" + fontPath + "'.");
		System.out.println("Face from file '" + fontPath + "' created.");

		/* --- Work with the face --- */

		/* --- Delete face --- */
		if (!FreeType.FT_Done_Face(face))
			throw new Exception("Error deleting face from file '" + fontPath + "'.");
		System.out.println("Face from file '" + fontPath + "' deleted.");
	}
}