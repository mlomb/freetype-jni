package examples;

import com.pvporbit.freetype.Face;
import com.pvporbit.freetype.FreeType;
import com.pvporbit.freetype.Library;

public class Example1 {

	public static void main(String args[]) throws Exception {
		String libName = "freetype26MT_x64";
		String fontPath = "res/OpenSans-Regular.ttf";

		/* --- Load library from selected native folder --- */
		// Native folder ${workspace_loc:FreeType-jni/freetype/builds/x64}
		System.loadLibrary(libName);
		System.out.println("Library loaded.");

		/* --- Init FreeType --- */
		Library library = FreeType.newLibrary();
		if (library == null)
			throw new Exception("Error initializing FreeType.");
		System.out.println("FreeType initialized.");

		/* --- Create face from .TTF --- */
		Face face = library.newFace(fontPath, 0);
		if (face == null)
			throw new Exception("Error creating face from file '" + fontPath + "'.");
		System.out.println("Face from file '" + fontPath + "' created.");

		/* --- Work with the face --- */

		/* --- Delete face --- */
		if (!face.delete())
			throw new Exception("Error deleting face from file '" + fontPath + "'.");
		System.out.println("Face from file '" + fontPath + "' deleted.");

		/* --- Destroy FreeType --- */
		if (!library.delete())
			throw new Exception("Error deleting FreeType.");
		System.out.println("FreeType deleted.");
	}
}