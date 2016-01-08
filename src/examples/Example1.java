package examples;

import com.pvporbit.freetype.Face;
import com.pvporbit.freetype.FreeType;
import com.pvporbit.freetype.GlyphSlot;
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
		// Change font size
		if (face.setPixelSizes(0, 30))
			throw new Exception("Error changing size.");

		System.out.println(face.getAscender());
		System.out.println(face.getCharIndex('b'));
		System.out.println(face.getDescender());
		System.out.println(face.getFaceFlags());
		System.out.println(face.getHeight());
		System.out.println(face.getMaxAdvanceHeight());
		System.out.println(face.getMaxAdvanceWidth());
		System.out.println(face.getNumGlyphs());
		System.out.println(face.getStyleFlags());
		System.out.println(face.getUnderlinePosition());
		System.out.println(face.getUnderlineThickness());
		System.out.println(face.getFaceIndex());
		System.out.println(face.getFamilyName());
		System.out.println(face.getNumFaces());
		System.out.println(face.getStyleName());
		System.out.println(face.getUnitsPerEM());
		System.out.println(face.hasKerning());
		System.out.println(face.loadChar('v', 0));
		GlyphSlot gs = face.getGlyphSlot();
		System.out.println(gs.getLinearHoriAdvance());
		System.out.println(gs.getLinearVertAdvance());
		System.out.println(gs.getAdvanceX());
		System.out.println(gs.getAdvanceY());
		System.out.println(gs.getFormat());
		System.out.println(gs.getBitmapLeft());
		System.out.println(gs.getBitmapTop());
		System.out.println("---------- Kerning -------------");
		for (char a = ' '; a < '~'; a++) {
			for (char b = ' '; b < '~'; b++) {
				int kern = (int) face.getKerning(a, b).getHorizontalKerning();
				if (kern != 0) {
					System.out.println(a + " - " + b + " = " + kern);
				}
			}
		}
		System.out.println("--------------------------------");

		/* --- Delete face --- */
		if (face.delete())
			throw new Exception("Error deleting face from file '" + fontPath + "'.");
		System.out.println("Face from file '" + fontPath + "' deleted.");

		/* --- Destroy FreeType --- */
		if (library.delete())
			throw new Exception("Error deleting FreeType.");
		System.out.println("FreeType deleted.");
	}
}